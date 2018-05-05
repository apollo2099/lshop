(function() {
	var conf = {
		weibo: {
			appid : '3366296453',
			script : 'http://tjs.sjs.sinajs.cn/open/api/js/wb.js',
			icon: 'http://res.mtvpad.com/www/res/pan/images/wb.gif',
			text: '微博'
		},
		weixin: {
			icon: 'http://res.mtvpad.com/www/res/pan/images/wx.gif',
			text: '微信'
		}
	};
	function seriesLoadScripts(scripts,callback) {
	   if(typeof(scripts) != "object") var scripts = [scripts];
	   var HEAD = document.getElementsByTagName("head").item(0) || document.documentElement;
	   var s = new Array(), last = scripts.length - 1, recursiveLoad = function(i) {  //递归
	       s[i] = document.createElement("script");
	       s[i].setAttribute("type","text/javascript");
	       s[i].onload = s[i].onreadystatechange = function() { //Attach handlers for all browsers
	           if(!/*@cc_on!@*/0 || this.readyState == "loaded" || this.readyState == "complete") {
	               this.onload = this.onreadystatechange = null; this.parentNode.removeChild(this); 
	               if(i != last) recursiveLoad(i + 1); else if(typeof(callback) == "function") callback();
	           }
	       }
	       s[i].setAttribute("src",scripts[i]);
	       HEAD.appendChild(s[i]);
	   };
	   recursiveLoad(0);
	}
	//活动分享
	var activityShare = {
		$hook: null,//分享按钮容器
		param: {},//活动参数
		_init : function(param){
			activityShare.$hook = this;
			$.extend(activityShare.param, param);
			$.post('/web/activity!getShareActivity.action', {activityCode: param.shareid}, function(detail){
				detail && (detail.shareImg || detail.shareText) && activityShare.shareWeibo(param.shareid, detail.shareImg, detail.shareText);
				detail && detail.shareLink && activityShare.shareWeixin(param.shareid, detail.shareLink);
				detail && activityShare.$hook.parent().show();
			});
		},
		//微博活动分享
		shareWeibo : function (actid, cimg, text){
			var wc = conf.weibo;
			this.$hook.append('<span id="c_wb_publish" style="cursor:pointer;"><img src="'+wc.icon+'" />'+wc.text+'</span><button id="wb_publish" style="display:none;"</button>');
			$('#c_wb_publish').click(function(e){
				if(!activityShare.param.beforeShare()){
					e.stopPropagation();
					return false;
				} else {
					$.get('/web/activity!shareValicode.action?t='+(new Date().getTime()));
					$('#wb_publish').trigger('click');
				}
			});
			seriesLoadScripts(wc.script+'?appkey='+wc.appid, function(){
				WB2.anyWhere(function(W){
					W.widget.publish({
						id : 'wb_publish',
						default_text : text,
						default_image : cimg,
						callback : function(o) {
							var pm = activityShare.param;
							pm.weiboSuccess(pm.shareid);
						}
					});
				});
			});
		},
		//微信活动分享
		shareWeixin: function(actid, link){
			this.$hook.append('<a id="c_wx_publish" style="cursor:pointer;"><img src="'+conf.weixin.icon+'" />'+conf.weixin.text+'</a>');
			$('#c_wx_publish').click(function(){
				if(!activityShare.param.beforeShare()) return false;
				var html = '<div id="c_wx_panel" style="position: fixed;z-index: 10000000001;"><div style="background-clip: padding-box;background-color: #FFFFFF;';
				html += 'border: 1px solid rgba(0, 0, 0, 0.3);border-radius: 6px 6px 6px 6px;box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);';
				html += 'left: 50%;margin: -200px 0 0 -200px;overflow: hidden;position: fixed;top: 50%;width: 360px;height: 360px;">';
				html += '<div style="border-bottom: 1px solid #EEEEEE;padding: 9px 15px;">';
				html += '<a href="javascript:void(0);" onclick="$(\'#c_wx_panel\').remove();" style="text-decoration: none;margin-top: 2px;color: #000000;float: right;font-size: 20px;';
				html += 'font-weight: bold;cursor: pointer;line-height: 20px;opacity: 0.2;text-shadow: 0 1px 0 #FFFFFF;">×</a>';
				html += '<h3 style="line-height: 30px;margin: 0;font-weight: normal;">分享到微信</h3></div>';
				html += '<div style="text-align: center;height: 251px;">';
				html += '<img style="margin-top: 25px;" src="';
				html += '/web/activity!wxqrcode.action?a='+actid+'&l='+link;
				html += '"/></div><div style="text-align: left;margin: 0 15px;padding: 0;font-size: 12px;">打开微信，点击底部的“发现”，使用 “扫一扫” 即可将网页分享给微信好友或者微信群。</div>';
				html += '</div></div>';
				$('body').append(html);
			});
		}
	}
	//暴露成jquery插件
	jQuery.fn.activityShare = activityShare._init;
})();