// Original deterministic demo music/art for the WalkTape launch film.
// Usage: node tools/Generate-VideoAssets.cjs <existing fixture dir> <existing audio dir>
const fs=require('node:fs'),path=require('node:path'),zlib=require('node:zlib');
const [fixtures,audio]=process.argv.slice(2);
for(const dir of [fixtures,audio])if(!fs.statSync(dir).isDirectory())throw Error('Existing output directory required');
const SR=44100,B=.625,TAU=2*Math.PI;
function wave(file,seconds,render){
 const count=Math.round(seconds*SR),out=Buffer.alloc(44+count*4);
 out.write('RIFF');out.writeUInt32LE(out.length-8,4);out.write('WAVEfmt ',8);
 out.writeUInt32LE(16,16);out.writeUInt16LE(1,20);out.writeUInt16LE(2,22);
 out.writeUInt32LE(SR,24);out.writeUInt32LE(SR*4,28);out.writeUInt16LE(4,32);out.writeUInt16LE(16,34);out.write('data',36);out.writeUInt32LE(count*4,40);
 let peak=0,sum=0;
 for(let i=0;i<count;i++){const t=i/SR,v=render(t,i);const fade=Math.min(1,t/.025,(seconds-t)/1.5);const x=Math.tanh(v*fade*.85);peak=Math.max(peak,Math.abs(x));sum+=x*x;const n=Math.round(x*32760);out.writeInt16LE(n,44+i*4);out.writeInt16LE(n,46+i*4);}
 fs.writeFileSync(file,out);return {seconds,peak,rms:Math.sqrt(sum/count)};
}
function noise(i){let x=(i^0x53a27)>>>0;x=Math.imul(x^x>>>16,0x45d9f3b);return((x>>>0)/4294967296)*2-1;}
function music(shift=0){return(t,i)=>{
 const beat=Math.floor(t/B),u=t%B,bar=Math.floor(beat/4),step=Math.floor(t/(B/2)),s=t%(B/2);
 const roots=[45,41,48,43],midi=roots[Math.floor(bar/2)%4]+shift;
 const hz=n=>440*2**((n-69)/12);
 const bass=.17*Math.sin(TAU*hz(midi)*t)*Math.exp(-u*5)*(1-Math.exp(-u*80));
 const kick=beat%2===0?.32*Math.sin(TAU*(48*u+5*(1-Math.exp(-u*25))))*Math.exp(-u*22):0;
 const snare=beat%4===1||beat%4===3?.095*noise(i)*Math.exp(-u*32):0;
 const hats=.025*(noise(i)-noise(i-1))*Math.exp(-s*85);
 const chord=[0,3,7].reduce((v,n)=>v+.032*Math.sin(TAU*hz(midi+24+n)*t),0)*(.65+.15*Math.sin(TAU*t/10));
 const notes=[0,7,12,10,3,7,15,12],note=hz(midi+24+notes[step%8]);
 const pluck=.065*(Math.sin(TAU*note*t)+.2*Math.sin(TAU*note*2*t))*Math.exp(-s*11)*(1-Math.exp(-s*100));
 return bass+kick+snare+hats+chord+pluck;
};}
const evidence={bpm:96,sampleRate:SR,channels:2,source:'Original procedural composition authored for WalkTape; no samples or third-party music',tracks:[]};
evidence.bgm=wave(path.join(audio,'walktape-original-96bpm.wav'),59.5,music());
wave(path.join(audio,'play-click.wav'),.08,(t,i)=>.3*noise(i)*Math.exp(-t*140)+.15*Math.sin(TAU*180*t)*Math.exp(-t*75));
for(const [n,shift] of [['01',0],['02',3],['03',-2]])evidence.tracks.push({file:n+'.wav',...wave(path.join(fixtures,n+'.wav'),48,music(shift))});
function crc32(b){let c=0xffffffff;for(const v of b){c^=v;for(let j=0;j<8;j++)c=(c>>>1)^((c&1)?0xedb88320:0);}return(c^0xffffffff)>>>0;}
function chunk(type,b){const t=Buffer.from(type),o=Buffer.alloc(b.length+12);o.writeUInt32BE(b.length);t.copy(o,4);b.copy(o,8);o.writeUInt32BE(crc32(Buffer.concat([t,b])),b.length+8);return o;}
const w=640,h=640,raw=Buffer.alloc((w*3+1)*h),blue=[42,77,155],cream=[244,241,234],orange=[240,106,33],night=[16,26,48];
for(let y=0;y<h;y++)for(let x=0;x<w;x++){
 let c=blue;if(x>60&&x<580&&y>175&&y<460)c=cream;
 if((x-205)**2+(y-305)**2<64**2||(x-435)**2+(y-305)**2<64**2)c=night;
 if((x-205)**2+(y-305)**2<20**2||(x-435)**2+(y-305)**2<20**2)c=cream;
 if(x>145&&x<495&&y>395&&y<418)c=orange;
 const p=y*(w*3+1)+1+x*3;c.forEach((v,j)=>raw[p+j]=v);
}
const ih=Buffer.alloc(13);ih.writeUInt32BE(w);ih.writeUInt32BE(h,4);ih[8]=8;ih[9]=2;
fs.writeFileSync(path.join(fixtures,'cover.png'),Buffer.concat([Buffer.from([137,80,78,71,13,10,26,10]),chunk('IHDR',ih),chunk('IDAT',zlib.deflateSync(raw)),chunk('IEND',Buffer.alloc(0))]));
fs.writeFileSync(path.join(audio,'provenance.json'),JSON.stringify(evidence,null,2));
console.log('Original 96 BPM base, click, three 48-second demo tracks and cover created; peak '+evidence.bgm.peak.toFixed(3));
