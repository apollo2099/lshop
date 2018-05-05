<%@ page language="java" pageEncoding="utf-8"%>

<article>
  <section>
       <div class="share">
          <div class="share_sh">
             <div class="sc">
                 <a onclick="AddFavorite(window.location,document.title)">收藏</a>
             </div>
             <div class="shareicon">
             <!-- JiaThis Button BEGIN -->
             <div class="jiathis_style_32x32">
                <span class="jiathis_txt" style="font-size:14px">分享到：</span>
	            <a class="jiathis_button_tsina"></a>
	            <a class="jiathis_button_fb"></a>
	            <a class="jiathis_button_twitter"></a>
	            <a class="jiathis_button_weixin"></a>
              	<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
             </div>
             <script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
             <!-- JiaThis Button END -->
          </div>
             <div class="clear"></div>
          </div>
       </div>
   </section>
</article>

<%@include file="/web/mbmcn/common/footer.jsp"%>