const JHCommonScriptFunction = function(){

    this.onEnterKeyDown = function(event, fn, fnParam) {
        //var keyCode = (window.event) ? event.which : event.keyCode;
        var keyCode = event.which || event.keyCode;		//KSY 20200504 임시수정
        if(keyCode === 13) {
            if(event.target && event.target.blur) {
                event.target && event.target.blur();
            }
            if(fn && typeof fn === 'function') {
                fn.apply(this, fnParam);
            }
            return false;
        }
        return true;
    }

};


/* ERP Common Script 사용을 위한 전역 변수에 Object 할당 */
const $jh = new JHCommonScriptFunction();