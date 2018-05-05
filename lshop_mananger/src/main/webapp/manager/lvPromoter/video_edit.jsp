<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPromtPayMngAction!editVideo.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="video.id" value="${video.id}"/> 
			<div class="pageFormContent" layoutH="56">
				 <p>
				     <label>视频网址：</label>
				     <input name="video.videoAdd" type="text" class="required" value="${video.videoAdd}"  alt="视频网址" />
				</p>
				<p>
					 <label>flash网址：</label>
				     <input name="video.flashAdd" type="text" class="required" value="${video.flashAdd}" alt="flash网址" />
				</p>
		        <p>
					 <label>html网址：</label>
				     <input name="video.htmlAdd" type="text" class="required" value="${video.htmlAdd}" alt="html网址" />
				</p>
				<p>
					 <label>视频名称：</label>
				     <input name="video.videoName" type="text" class="required" value="${video.videoName}" maxlength="32" alt="视频名称" />
				</p>
				<p style="height:48px;">
					<label>视频描述：</label>
					<textarea name="video.videoDesc" class="required" cols="20" rows="2" lang="视频描述">${video.videoDesc}</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>

		</form>
	</div>
</div>