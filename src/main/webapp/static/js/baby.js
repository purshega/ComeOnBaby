$(document).ready(function () {
    $('.btnOpenCloseInfo').click(function() {
        var num = $(this).attr('par');
        var status = 1 - $(this).attr('status');
        $(this).attr('status', status);
        if(status==0){
            $(this).html('+');
            $('.BlockInfo'+num).css('display', 'none');
        } else {
            $(this).html('-');
            $('.BlockInfo'+num).css('display', 'block');
        }
    });
    $('.btnAddBlock').click(function() {
        $(this).css('display', 'none');
        $('.divAddBlock, .btnSaveBlock, .btnCancelSaveBlock').css('display', 'block');
    });
    $('.btnCancelSaveBlock').click(function() {
        $('.divAddBlock, .btnSaveBlock, .btnCancelSaveBlock').css('display', 'none');
        $('#textAddNewNotice').val('');
        $('.btnAddBlock').css('display','block');
    });
    $('.btnCancelSaveBlock').click(function() {

    });
    /*
    $('.btnEditTextImg').click(function() {
        var num = $(this).attr('par');
        $('.btnRemoveTextImg'+num+', .btnSaveTextImg'+num+', .btnCancelTextImg'+num).css('display', 'inline-block');
        $(this).css('display', 'none');
    });
    $('.btnRemoveTextImg').click(function() {
        var num = $(this).attr('par');
        $('.BlockInfoItem'+num).css('display', 'none');
    });
    $('.btnSaveTextImg').click(function() {
        var num = $(this).attr('par');
        if($(this).attr('type')==0) saveImgBlock('textarea', num);
        else alert('imgs not yet!');
    });
    */
    $('.btnAddText').click(function() {
        var num = $(this).attr('par');//i
        var type = 0;
        var count = $('#BlockInfo'+num).attr('count');
        var code = $('#BlockInfo'+num).attr('code');
        //sendAjax('addImgText', code, type, count, num);

        $('#BlockInfo'+num).attr('count', 1+1*count);

        var numItem = num + '_' + count;

        var div = document.createElement("div");
        div.className = 'BlockInfoItem' + numItem;
        div.id = 'BlockInfoItem' + numItem;
        var attrType = document.createAttribute("type");
        attrType.value = "0";
        div.setAttributeNode(attrType);
        var attrCode = document.createAttribute("code");
        attrCode.value = "0";
        div.setAttributeNode(attrCode);
        var attrSort = document.createAttribute("sort");
        attrSort.value = count;
        div.setAttributeNode(attrSort);

        var res = document.createElement('textarea');
        res.cols = '80';
        var attrCode1 = document.createAttribute("code");
        attrCode1.value = "0";
        res.setAttributeNode(attrCode1);
        res.id='BlockInfoItemTextarea'+numItem;
        var hr = document.createElement('hr');
        //var br = document.createElement('br');
        div.appendChild(hr);
        div.appendChild(res);

        var base = document.getElementById('BlockInfo'+num);
        base.appendChild(div);

    });
    $('.btnEditTitleBlock').click(function() {
        var num = $(this).attr('par');
        $('.Editable'+num).css('display','inline-block');
        $('.BlockEditable'+num).css('display','block');
        $('.NotEditable'+num+' .NotBlockEditable'+num).css('display', 'none');
        $('#titleBlockInfo'+num).prop('disabled', false);
        $(this).css('display','none');

    });
    $('.btnSaveTitleBlock').click(function() {
        var num = $(this).attr('par');
        var title = $('#titleBlockInfo'+num).val();
        var code = $(this).attr('code');
        $(this).css('display','none');
        $('#reserv_titleBlockInfo'+num).val(title);
        var count = $('#BlockInfo'+num).attr('count');
        //
        /*
        var mas = {};
        mas['notice'] = code;
        mas['title'] = title;
        var str = '[';
        for(var j = 0; j<count;j++) {
            var arr = {};
            arr['code'] = $('#BlockInfoItem' + num + '_' + j).attr('code');
            arr['value'] = $('#BlockInfoItemTextarea' + num + '_' + j).val();
            arr['sort'] = $('#BlockInfoItem' + num + '_' + j).attr('sort');
            arr['type'] = 0;

            if(j>0) str += ', ';
            str += JSON.stringify(arr);
        }
        str += ']';
        mas['texts'] = str;
        console.log(JSON.stringify(mas));
        */
        //
        var str = '{';

        str += "notice:'"+code;
        str += "',title:'"+title;
        str += "',texts:[";
        for(var j = 0; j<count;j++) {
            if(j>0) str +=', ';
            str += "{";
            str += "code:'"+$('#BlockInfoItem'+num+'_'+j).attr('code');
            str += "',value:'"+$('#BlockInfoItemTextarea'+num+'_'+j).val();
            str += "',sort:'"+$('#BlockInfoItem'+num+'_'+j).attr('sort');
            str += "',type:0";
            str += "}";
        }
        str += "]}";
        console.log('str='+str);
        //sendAjax('saveTitleBlock', code, num, title, num);
        sendAjax('saveTitleBlock', str, num, count);
    });
    $('.btnCancelTitleBlock').click(function() {
        var num = $(this).attr('par');
        var title = $('#reserv_titleBlockInfo'+num).val();
        $('#titleBlockInfo'+num).val(title);
        $('.NotEditable'+num).css('display','inline-block');
        $('.NotblockEditable'+num).css('display','block');
        $('.Editable'+num+' .BlockEditable'+num).css('display', 'none');
        $('#titleBlockInfo'+num).prop('disabled', true);
    });
    $('.DTTT_button_Print').click(function() {
        alert(1);
    });

});
/*
function saveImgBlock(type, num){
    var value = '';
    if(type=='textarea') value = $('#BlockInfoItemTextarea'+num).val();
    var code = $('.btnSaveTextImg'+num).attr('code');
    sendAjax('saveImgText', code, 'textarea', value, num);
}
*/
function sendAjax(operation, code, num, count){
    $.ajax({
        type: "POST",
        data: 'operation='+operation+'&code='+code,
        url: '../../ajax/action',
        dataType: "json",
        success: function(data){
            if(operation=='saveTitleBlock'){
                $('.NotEditable'+num).css('display','inline-block');
                $('.NotBlockEditable'+num).css('display','block');
                $('.BlockEditable'+num+' .Editable'+num).css('display', 'none');
                $('#titleBlockInfo'+num).prop('disabled', true);

                var ans = data.answer;
                console.log('data.answer='+data.answer);
                if(ans!=undefined){
                    var arr = (""+ans).split(",");
                    //alert(arr[0] + '/'+ arr[1]);
                    for(var j = 0;j<count;j++){
                        $('#BlockInfoItem'+num+'_'+j).attr('code', arr[j]);
                    }
                }
                if(data.html!=undefined){
                    $('.BlockInfoHTML'+num).html(data.html);
                }
                //
                $('.btnOpenCloseInfo'+num).attr('status', 0);
                $('.btnOpenCloseInfo'+num).html('+');
                $('.BlockInfo'+num).css('display', 'none');
                /*
                 $('.btnOpenCloseInfo').click(function() {
                 var num = $(this).attr('par');
                 var status = 1 - $(this).attr('status');
                 $(this).attr('status', status);
                 if(status==0){
                 $(this).html('+');
                 $('.BlockInfo'+num).css('display', 'none');
                 } else {
                 $(this).html('-');
                 $('.BlockInfo'+num).css('display', 'block');
                 }

                 */
            }
        },
        error: function(){
            alert('ajax_error');
        },
    });

}

