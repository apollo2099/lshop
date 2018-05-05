/**
 * 可搜索下拉框组件
 * JinJian 2014-11-04
 * 示例：
	<div class="searchSelect" id="search1">
		<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
	</div> 
 * 	 var ss = new SearchSelect('search1');
 *   var countryData=[{"id":100001,"name":"Afghanistan","icon":"/res/images/country/af.gif","code":"AF"}];
 *   ss.setData(countryData);
 *   ss.getSelectedId();
 */
function SearchSelect(ssId){
	var $searchSelectList;
	var $searchSelectInput;
	var $searchSelectDown;
	var _keySet = {
		downArrow:40,
		upArrow:38,
		enter:13
	};
	var _datas=[];
	var _listId = ssId + '-list';
	var _oldSelectedId = -1;
	var _selectedId = -1;
	var _highlightId = -1;
	var _filterDatasSelectedIndex = -1;
	var _filterDatas = [];
	var _ssItemPrev = 'ssItem-';
	var _maxRow = 10;
	var _maxRowHeight = 245;
	//输入框宽度
	var _inputWidth = 100;
	//下拉箭头span元素宽度
	var _downSpanWidth = 20;
	var _onchanged;
	var _tipText = {
		hasValue:false,
		text:''
	};
	
	var _init = function(){
		$searchSelectInput = $('#'+ssId + ' .searchSelectInput').attr('id',ssId+'-input').attr('autocomplete','off');
		$searchSelectDown = $('#'+ssId + ' .searchSelectDown').attr('id',ssId+'-down');
		$('body').append('<div id="'+ssId+'-list" class="searchSelectList"></div>');
		$searchSelectList = $('#'+_listId);		
			
		$searchSelectList.mouseover(function(event){
			var targetId = event.target.id;
			if (targetId && (targetId.indexOf(_ssItemPrev)>-1)) {
				var itemId = targetId.replace(_ssItemPrev,'');
				if (itemId != _highlightId) {
					_highlightId = itemId;
					highlightItem();
				}					
			}
		});
		
		$searchSelectList.mouseout(function(event){
			var targetId = event.target.id;
			if (targetId && (targetId.indexOf(_ssItemPrev)==-1)) {
				var itemId = targetId.replace(_ssItemPrev,'');		
					_highlightId = -1;
					$('#'+_listId + ' .ssItem').removeClass('selected');			
			}
		});		
		
		$searchSelectList.click(function(event){
			doEnter();
		});
		
		$searchSelectInput.keyup(function(event){
			if (event.keyCode == _keySet.downArrow) {
				doDownArrow();
			} else if (event.keyCode == _keySet.upArrow) {
				doUpArrow();
			} else if (event.keyCode == _keySet.enter) {
				doEnter();
			} else {
				var fs = $searchSelectInput.val();
				_filter(fs);			
				_clearSelected();
				if (_filterDatas.length>0) {				
					showList();							
				} else {					
					hideList();
				}
			}
		});
		
		$searchSelectInput.focus(function(event){
			if (_tipText.hasValue && (_tipText.text == $searchSelectInput.val())) {
				$searchSelectInput.val('');
			}
		});
		

		$searchSelectInput.blur(function(event){
			doEnter();
		});
		
		$searchSelectDown.click(function(){
			_filterDatas = _datas;
			if (_filterDatas.length>0) {
				if (_selectedId < 0) {
					setSelectedIdIndex(-1, -1);
				}					
				showList();
				highlightItem();					
			} else {
				setSelectedIdIndex(-1, -1);
				hideList();
			}
			$searchSelectList.attr('scrollTop',0);
			return false;
		});
	};
	_init();
	
	var doDownArrow = function(){
		if (_filterDatasSelectedIndex < _filterDatas.length-1) {
			_filterDatasSelectedIndex++;
			var id = _filterDatas[_filterDatasSelectedIndex].id;
			_highlightId = id;
			highlightItem();
			scrollInViewDownArrow();
		}
	}
	var scrollInViewDownArrow = function(){
		if (_filterDatas.length > _maxRow) {
			var $item = $('#'+_ssItemPrev+_highlightId);
			var itemOffsetTop = $item.attr('offsetTop');
			var itemClientHeight = $item.attr('clientHeight');
			var listScrollTop = $searchSelectList.attr('scrollTop');
			if ((itemOffsetTop+itemClientHeight-listScrollTop) > _maxRowHeight) {
				$searchSelectList.attr('scrollTop',itemOffsetTop+itemClientHeight-_maxRowHeight);
			}
		}	
	}
	
	var scrollInViewUpArrow = function(){
		if (_filterDatas.length > _maxRow) {
			var $item = $('#'+_ssItemPrev+_highlightId);
			var itemOffsetTop = $item.attr('offsetTop');
			var itemClientHeight = $item.attr('clientHeight');
			var listScrollTop = $searchSelectList.attr('scrollTop');
			if ((itemOffsetTop-listScrollTop) < 0) {
				$searchSelectList.attr('scrollTop',itemOffsetTop);
			}
		}	
	}	
	
	var doUpArrow = function(){
		if (_filterDatasSelectedIndex > 0) {
			_filterDatasSelectedIndex--;
			var id = _filterDatas[_filterDatasSelectedIndex].id;
			_highlightId = id;
			highlightItem();
			scrollInViewUpArrow();
		}	
	}

	var doEnter = function(){
		if (_filterDatas.length > 0) {
			for (var i=0;i<_filterDatas.length;i++) {
				if (_highlightId == _filterDatas[i].id) {
					setSelectedIdIndex(_filterDatas[i].id, i);
				}
			}
			setCurrentText();		
			if (_onchanged && (_selectedId != _oldSelectedId))  {
				_onchanged(_selectedId, _oldSelectedId);
			}
			_oldSelectedId = _selectedId;			
		} else {
			setSelectedIdIndex(-1, -1);
			if (_onchanged && (_selectedId != _oldSelectedId))  {
				_onchanged(_selectedId, _oldSelectedId);
			}
			_oldSelectedId = _selectedId;		
			_clearSelected();
		}

		hideList();		
	}
	
	var setCurrentText = function(){
		if (_filterDatasSelectedIndex > -1) {
			$searchSelectInput.val(_filterDatas[_filterDatasSelectedIndex].name);
		}		
	}
	var setSelectedIdIndex = function(id, index){		
		_selectedId = id;
		_filterDatasSelectedIndex = index;
	}
	
	var highlightItem = function(){
		if (_highlightId > 0) {
			$('#'+_listId + ' .ssItem').removeClass('selected');
			$('#'+_ssItemPrev+_highlightId).addClass('selected');		
		}
	}
	
	var refreshInputPos = function(){
		var inputPosition = $searchSelectInput.offset();
		var left = inputPosition.left;
		var top = inputPosition.top + $searchSelectInput.height() +3;
		$searchSelectList.css('left',left).css('top',top);	
	}
	
	var showList = function(){
		if (_filterDatas.length>0) {
			$('#'+_listId + ' .ssItem').hide();
			for (var i=0;i<_filterDatas.length;i++) {
				$('#'+_ssItemPrev+_filterDatas[i].id).show();
			}
			if (_filterDatas.length>_maxRow) {
				$searchSelectList.css('height',_maxRowHeight+'px');
			} else {
				$searchSelectList.css('height','auto');
			}
			refreshInputPos();
			$searchSelectList.show();	
			$(document).bind('click',hideList);			
		}
	}
	
	/**匹配展示模式
	1、当源数据条数为0时，展示为input输入框模式；
	2、当源数据条数大于0时，展示为下拉框选择模式；
	*/
	var matchShowMode = function(){
		if (_datas.length > 0) {
			$searchSelectInput.css('width', _inputWidth + 'px');
			$searchSelectDown.css('width', _downSpanWidth+'px');
		} else {	
			$searchSelectInput.css('width', (_inputWidth+_downSpanWidth) + 'px');
			$searchSelectDown.css('width', '0px');
		}
	}
	
	var hideList = function(){
		$searchSelectList.hide();
		$(document).unbind('click',hideList);		
	}	
	/*设置数据*/
	this.setData = function(data){
		_datas = data;
		matchShowMode();
		_filterDatas = _datas;
		var liHtmls='';
		for (var i=0;i<_datas.length;i++) {
			liHtmls = liHtmls + '<li id="'+_ssItemPrev+''+_datas[i].id+'" class="ssItem">'+_datas[i].name+'</li>';
		}
		$searchSelectList.html('<ul>'+liHtmls+'</ul>');
		_clearSelected();
		$searchSelectInput.val('');
	}
	/*检查输入框数据是否合格*/
	this.existInData = function(){
		var val = $searchSelectInput.val();
		for (var i=0;i<_datas.length;i++) {
			if(val == _datas[i].name){
				return true;
			}
		}
		return false;
	}
	
	/*检查是否为空数据*/
	this.isEmptyData = function(){
		for (var i=0;i<_datas.length;i++) {
			if(_datas[i].id > 0){
				return false;
			}
		}
		return true;
	}	
	
	/*返回当前输入框文本匹配的id*/
	this.getSelectedId = function(){
		var val = $searchSelectInput.val();
		for (var i=0;i<_datas.length;i++) {
			if(val == _datas[i].name){
				return _datas[i].id;
			}
		}	
		return -1;
	}
	
	/*设置当前输入框文本匹配的id*/
	this.setSelectedId = function(id){
		_filterDatas = _datas;
		for (var i=0;i<_filterDatas.length;i++) {
			if(id == _filterDatas[i].id){
				setSelectedIdIndex(_filterDatas[i].id, i);
				_oldSelectedId = id;
				highlightItem();
				setCurrentText();				
				return;
			}
		}
	}
	
	var _clearSelected = function(){
		$('#'+_listId + ' .ssItem').removeClass('selected');
		_highlightId = -1;
		setSelectedIdIndex(-1, -1);
		_oldSelectedId = -1;		
	}
	/*清除选择*/
	this.clearSelected = function(){
		_clearSelected();
		$searchSelectInput.val('');	
	}
	
	/*返回当前输入框文本匹配的文本*/
	this.getSelectedText = function(){	
		if (_selectedId>0) {
			for (var i=0;i<_datas.length;i++) {
				if(_selectedId == _datas[i].id){
					return _datas[i].name;
				}
			}
		}		
		return '';
	}

	/*返回当前输入框的文本*/
	this.getText = function(){	
		return $searchSelectInput.val();
	}	
	
	/*设置当前输入框的文本*/
	this.setText = function(text){	
		return $searchSelectInput.val(text);
	}	
	
	/*设置当前输入框的提示文本信息*/
	this.setTipText = function(text){
		_tipText.hasValue = true;
		_tipText.text = text;
		$searchSelectInput.val(text);
	}	
	
	/*选项变化事件*/
	this.onchanged = function(func){
		_onchanged = func;
	};
	
	var _filter = function(fs){
		_filterDatas = [];
		fs = fs.toLowerCase();
		if (fs.length > 0) {
			for (var i=0;i<_datas.length;i++) {
				if((_datas[i].name.toLowerCase().indexOf(fs)>=0) || (_datas[i].nameTw.toLowerCase().indexOf(fs)>=0) || (_datas[i].nameCn.toLowerCase().indexOf(fs)>=0)){					
					_filterDatas.push(_datas[i]);				
				}
			}		
		}
	}
}