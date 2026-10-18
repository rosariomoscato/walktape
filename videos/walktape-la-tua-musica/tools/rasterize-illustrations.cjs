const path=require('node:path');
const {pathToFileURL}=require('node:url');
const puppeteer=require('puppeteer');
(async()=>{
 const dir=path.resolve(__dirname,'../assets/illustrations');
 const browser=await puppeteer.launch({headless:true,timeout:60000,args:['--no-sandbox','--disable-dev-shm-usage']});
 try {
  const page=await browser.newPage();await page.setViewport({width:1080,height:1400,deviceScaleFactor:1});
  for(const name of ['01-raccolta','02-mixtape','03-offline']){
   await page.goto(pathToFileURL(path.join(dir,name+'.svg')).href,{waitUntil:'load'});
   await page.screenshot({path:path.join(dir,name+'.png'),type:'png'});
   console.log('Rasterized '+name+' 1080x1400');
  }
 } finally {await browser.close();}
})().catch(e=>{console.error(e.message);process.exitCode=1;});
