// Original local synth-pop score. No samples, models, or third-party melodies.
// 120 BPM: four-on-the-floor kick, gated electronic snare/clap, octave bass,
// detuned poly-synth chords, sixteenth-note sequencer, and an original lead hook.
const fs=require('node:fs'),path=require('node:path');
const dir=path.resolve(__dirname,'../assets/audio');
if(!fs.statSync(dir).isDirectory())throw Error('Existing audio directory required');
const SR=44100,B=.5,DURATION=59.2,N=Math.round(SR*DURATION),T=2*Math.PI;
const L=new Float64Array(N),R=new Float64Array(N);
const hz=m=>440*2**((m-69)/12);
function noise(i,salt=0){let v=(i+Math.imul(salt,374761393))|0;v=Math.imul(v^(v>>>13),1274126177);return((v^(v>>>16))>>>0)/2147483648-1;}
function note(start,duration,fn,gain,pan=0){
 const a=Math.round(start*SR),len=Math.round(duration*SR),gl=Math.sqrt((1-pan)/2),gr=Math.sqrt((1+pan)/2);
 for(let j=0;j<len;j++){const i=a+j;if(i<0||i>=N)continue;const v=fn(j/SR,j)*gain;L[i]+=v*gl;R[i]+=v*gr;}
}
const roots=[45,41,48,43];
const rootAt=t=>t>=56?45:t<8?45:roots[Math.floor((t-8)/4)%4];
const chordAt=t=>{const root=rootAt(t);return [root+12,root+19,root+24,root+(root===45?27:28)];};
// Physical attack/body in the kick, with a short pitch fall rather than noise.
for(let b=0;b*B<DURATION;b++){
 const at=b*B;
 const main=at>=8&&at<56;
 note(at,.34,t=>Math.sin(T*(49*t+3.4*(1-Math.exp(-t*38))))*Math.exp(-t*15)+.11*Math.sin(T*1800*t)*Math.exp(-t*180),.8);
 if(b%4===1||b%4===3){
  note(at,.24,(t,i)=>{
   const gate=Math.min(1,(.24-t)/.025);
   const body=Math.sin(T*184*t)*Math.exp(-t*19);
   const snap=(noise(i,71)-noise(i-1,71))*.5;
   const flam=([0,.012,.026].reduce((s,off)=>s+(t>=off?Math.exp(-(t-off)*85):0),0));
   const gatedRoom=noise(i,913)*Math.exp(-t*7)*.28;
   return (.45*body+snap*flam*.45+gatedRoom)*gate;
  },.62);
 }
 // Offbeat open hats and a steady sixteenth grid in the main sections.
 for(let sub=0;sub<(main?4:2);sub++){
  const st=at+sub*B/(main?4:2),open=(sub===(main?2:1));
  note(st,open?.17:.065,(t,i)=>(noise(i,19)-noise(i-1,19))*Math.exp(-t*(open?25:65)),open?.085:.055,sub%2?.45:-.45);
 }
 // Original octave-synth bass pattern with syncopated short pickups.
 if(at<56){
  for(let sub=0;sub<2;sub++){
   const st=at+sub*B/2,f=hz(rootAt(st)+(sub===1&&b%2===1?12:0));
   note(st,.225,t=>{
    const env=(1-Math.exp(-t*250))*Math.exp(-t*10)*Math.min(1,(.225-t)/.025);
    return (Math.sin(T*f*t)+.28*Math.sin(T*f*2*t)+.1*Math.sin(T*f*3*t))*env;
   },.43);
  }
 }
 // Brief end-of-eight-bars fills, instead of a copy-pasted unchanging loop.
 if(main&&b%16===15){for(let k=1;k<=3;k++)note(at+k*.115,.075,(t,i)=>noise(i,200+k)*Math.exp(-t*45),.12,k%2?.3:-.3);}
}
// Detuned poly-synth stabs, bright enough to read as eighties synth-pop.
for(let k=0;k*B<DURATION;k++){
 const at=k*B+(k%2?.25:0),chord=chordAt(at),active=at>=8&&at<56;
 if(!active&&k%4!==0)continue;
 for(const [n,m] of chord.entries()){
  const f=hz(m),length=active?.42:1.35;
  note(at,length,t=>{
   const env=(1-Math.exp(-t*170))*Math.exp(-t*(active?5:2))*Math.min(1,(length-t)/.04);
   const poly=Math.sin(T*f*t)+.38*Math.sin(T*f*2*t)+.15*Math.sin(T*f*3*t)+.42*Math.sin(T*f*1.004*t)+.32*Math.sin(T*f*.996*t);
   return poly*env;
  },active?.09:.07,(n-1.5)*.33);
 }
}
// Sixteenth sequencer and octave variation enter after the first four bars.
const arp=[0,7,12,19,12,7,3,10,0,7,15,19,12,10,7,3];
for(let step=0;8+step*B/4<56;step++){
 const at=8+step*B/4,f=hz(rootAt(at)+24+arp[step%arp.length]);
 const level=at>=24&&at<40?.055:.036;
 for(const [delay,gain] of [[0,1],[.25,.24],[.5,.12]])note(at+delay,.19,t=>(Math.sin(T*f*t)+.22*Math.sin(T*f*2*t))*(1-Math.exp(-t*350))*Math.exp(-t*24),level*gain,delay===0?-.25:.55);
}
// An original eight-bar hook; no quoted or imitated existing tune.
const hook=[12,null,15,19,17,15,12,10,12,null,19,22,19,17,15,12];
for(let start=16;start<56;start+=16){
 for(let k=0;k<hook.length;k++){
  if(hook[k]===null)continue;const at=start+k*B,f=hz(45+12+hook[k]);
  for(const [delay,gain] of [[0,1],[.25,.25]])note(at+delay,.43,t=>{
   const env=Math.min(1,t/.012)*Math.exp(-t*6)*Math.min(1,(.43-t)/.045);
   const vibrato=.002*Math.sin(T*5.2*t);return(Math.sin(T*f*t*(1+vibrato))+.3*Math.sin(T*f*2*t)+.13*Math.sin(T*f*3*t))*env;
  },.085*gain,delay?-.35:.2);
 }
}
// Final A-minor resolution leaves room for the held closing card.
for(const [n,m] of [45,57,64,69,72].entries())note(56,3.2,t=>Math.sin(T*hz(m)*t)*Math.min(1,t/.04)*Math.exp(-t*.6)*Math.min(1,(3.2-t)/.8),.12,(n-2)*.25);
// Deterministic bus saturation and peak normalisation, no hard clipping.
let peak=0;
for(let i=0;i<N;i++){
 const fade=Math.min(1,i/SR/.015,(DURATION-i/SR)/.3);
 L[i]=Math.tanh(L[i]*1.35)*fade;R[i]=Math.tanh(R[i]*1.35)*fade;
 peak=Math.max(peak,Math.abs(L[i]),Math.abs(R[i]));
}
const targetPeak=10**(-1.7/20),gain=targetPeak/peak,buffer=Buffer.alloc(44+N*4);
buffer.write('RIFF');buffer.writeUInt32LE(buffer.length-8,4);buffer.write('WAVEfmt ',8);buffer.writeUInt32LE(16,16);buffer.writeUInt16LE(1,20);buffer.writeUInt16LE(2,22);buffer.writeUInt32LE(SR,24);buffer.writeUInt32LE(SR*4,28);buffer.writeUInt16LE(4,32);buffer.writeUInt16LE(16,34);buffer.write('data',36);buffer.writeUInt32LE(N*4,40);
let power=0;
for(let i=0;i<N;i++){const l=L[i]*gain,r=R[i]*gain;power+=l*l+r*r;buffer.writeInt16LE(Math.round(l*32767),44+i*4);buffer.writeInt16LE(Math.round(r*32767),46+i*4);}
const name='walktape-retro80-120bpm.wav';fs.writeFileSync(path.join(dir,name),buffer);
const meta={file:name,bpm:120,durationSeconds:DURATION,sampleRate:SR,channels:2,peakDbfs:-1.7,rmsDbfs:20*Math.log10(Math.sqrt(power/(N*2))),source:'Original locally composed synth-pop score; no samples, model generation or third-party melodies',instruments:['four-on-the-floor electronic kick','gated electronic snare and clap','sixteenth hats','syncopated octave synth bass','detuned poly-synth stabs','sequencer arpeggio','original delayed lead hook'],form:'0–8 intro; 8–24 main A; 24–40 sequencer variation; 40–56 main reprise; 56–59.2 resolution',generator:'tools/compose-retro80.cjs'};
fs.writeFileSync(path.join(dir,'retro80-provenance.json'),JSON.stringify(meta,null,2));
console.log(`Composed ${name}: ${DURATION}s, 120 BPM, peak -1.7 dBFS, RMS ${meta.rmsDbfs.toFixed(1)} dBFS.`);
