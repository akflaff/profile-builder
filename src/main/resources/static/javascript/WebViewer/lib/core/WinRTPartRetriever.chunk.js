/** Notice * This file contains works from many authors under various (but compatible) licenses. Please see core.txt for more information. **/
(function(){(window.wpCoreControlsBundle=window.wpCoreControlsBundle||[]).push([[18],{393:function(Ca,ua,r){r.r(ua);var pa=r(1),ja=r(215);Ca=r(383);var ka=r(84);r=r(315);var ea={},da=function(ba){function w(y,f){var e=ba.call(this,y,f)||this;e.url=y;e.range=f;e.status=ja.a.NOT_STARTED;return e}Object(pa.c)(w,ba);w.prototype.start=function(y){var f=this;"undefined"===typeof ea[this.range.start]&&(ea[this.range.start]={lI:function(e){var a=atob(e),b,n=a.length;e=new Uint8Array(n);for(b=0;b<n;++b)e[b]=a.charCodeAt(b);
a=e.length;b="";for(var h=0;h<a;)n=e.subarray(h,h+1024),h+=1024,b+=String.fromCharCode.apply(null,n);f.lI(b,y)},pha:function(){f.status=ja.a.ERROR;y({code:f.status})}},window.external.notify(this.url),this.status=ja.a.STARTED);f.qz()};return w}(Ca.ByteRangeRequest);Ca=function(ba){function w(y,f,e,a){y=ba.call(this,y,e,a)||this;y.kv=da;return y}Object(pa.c)(w,ba);w.prototype.rt=function(y,f){return y+"?"+f.start+"&"+(f.stop?f.stop:"")};return w}(ka.a);Object(r.a)(Ca);Object(r.b)(Ca);ua["default"]=
Ca}}]);}).call(this || window)