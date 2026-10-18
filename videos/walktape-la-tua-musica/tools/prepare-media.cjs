const fs=require('node:fs'),path=require('node:path'),{execFileSync}=require('node:child_process');
const ffmpeg=require('ffmpeg-static'),root=path.resolve(__dirname,'..'),out=path.join(root,'assets/media'),src=path.join(root,'assets/captures');
if(!fs.existsSync(out))throw Error('assets/media must exist');
const run=args=>execFileSync(ffmpeg,['-hide_banner','-loglevel','error','-y',...args],{stdio:'inherit'});
const media=[
 ['library.png','library.png','crop=1080:1400:0:120'],
 ['album.png','album.png','crop=1080:1400:0:240'],
 ['queue.png','queue.png','crop=1080:640:0:1190,pad=1080:1400:0:380:color=0x101A30'],
 ['queue-named.png','queue-named.png','crop=1080:640:0:1190,pad=1080:1400:0:380:color=0x101A30'],
 ['mixtape-name.png','mixtape-name.png','crop=900:620:90:660,pad=1080:1400:90:390:color=0x101A30'],
 ['save-playlist.png','save-playlist.png','crop=900:620:90:660,pad=1080:1400:90:390:color=0x101A30'],
 ['playlist-saved.png','playlist-saved.png','crop=1080:1400:0:120'],
 ['playlist-detail.png','playlist-detail.png','crop=1080:1400:0:240'],
 ['download-complete.png','download-complete.png','crop=1080:1100:0:725,pad=1080:1400:0:150:color=0x101A30'],
 ['offline-library.png','offline-library.png','crop=1080:1400:0:120'],
 ['player-paused.png','player-paused.png','crop=1080:1400:0:240']
];
for(const [input,output,filter] of media)run(['-i',path.join(src,input),'-vf',filter,'-frames:v','1',path.join(out,output)]);
for(const [input,output] of [['player-play.mp4','player.mp4'],['offline-player.mp4','offline.mp4']])run(['-i',path.join(src,input),'-vf','crop=1080:1400:0:240,fps=30','-an','-c:v','libx264','-preset','fast','-crf','18','-pix_fmt','yuv420p',path.join(out,output)]);
const xml=fs.readFileSync(path.resolve(root,'../../app/src/main/res/drawable/ic_launcher_foreground.xml'),'utf8');
const paths=[...xml.matchAll(/<path\s+([\s\S]*?)\/>/g)].map(m=>{
 const a=Object.fromEntries([...m[1].matchAll(/android:(\w+)="([^"]*)"/g)].map(v=>[v[1],v[2]]));
 return `<path fill="${a.fillColor}"${a.strokeColor?` stroke="${a.strokeColor}" stroke-width="${a.strokeWidth}"`:''} d="${a.pathData}"/>`;
});
fs.writeFileSync(path.join(out,'walktape-icon.svg'),`<svg xmlns="http://www.w3.org/2000/svg" width="220" height="220" viewBox="0 0 108 108"><rect width="108" height="108" rx="22" fill="#2A4D9B"/>${paths.join('')}</svg>`);
console.log('Prepared '+media.length+' real still crops, two 30fps silent recordings, and faithful Android icon conversion.');
