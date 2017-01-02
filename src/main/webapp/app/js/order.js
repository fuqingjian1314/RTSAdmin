mui.init({
    swipeBack: true //启用右滑关闭功能
});
var codeNames={"0":"0","1":"1","2":"2","3":"3","4":"4","5":"5","6":"6","7":"7","8":"8","9":"9","big":"大","small":"小","single":"单","double":"双","sumbig":"总和大","sumsmall":"总和小","sumsingle":"总和单","sumdouble":"总和双","dragon":"龙","tiger":"虎","peace":"和","befleopard":"前豹子","befstraight":"前顺子","befpair":"前对子","befhalfstraight":"前半顺","befmix6":"前杂六","midleopard":"中豹子","minstraight":"中顺子","minpair":"中对子","minhalfstraight":"中半顺","minmix6":"中杂六","aftleopard":"后豹子","aftstraight":"后顺子","aftpair":"后对子","afthalfstraight":"后半顺","aftmix6":"后杂六"};
var controlNames=["","第一球","第二球","第三球","第四球","第五球","总和、龙虎"];
var contentNames=["","大","小","单","双","0","1","2","3","4","5","6","7","8","9"];
var contentTypes=["","big","small","single","double","0","1","2","3","4","5","6","7","8","9"];
var contentTimes=["","1.9","1.9","1.9","1.9","9","9","9","9","9","9","9","9","9","9"];
var contentMap={};
for(var i=1;i<=contentTypes.length;i++){
    contentMap[contentTypes[i]]=contentTimes[i];
}
var sumNames=["总和大","总和小","总和单","总和双","龙","虎","和"];
var sumTimes=["1.9","1.9","1.9","1.9","1.9","1.9","9"];
var sumTypes=["sumbig","sumsmall","sumsingle","sumdouble","dragon","tiger","peace"];
var sumMap={};
for(var i=0;i<=sumTypes.length;i++){
    sumMap[sumTypes[i]]=sumTimes[i];
}
var colligateNames=["豹子","顺子","对子","半顺","杂六"];
var colligateTypes=["leopard","straight","pair","halfstraight","mix6"];
var colligateTypes_q=["befleopard","befstraight","befpair","befhalfstraight","befmix6"];
var colligateTypes_z=["midleopard","minstraight","minpair","minhalfstraight","minmix6"];
var colligateTypes_h=["aftleopard","aftstraight","aftpair","afthalfstraight","aftmix6"];
var colligateTimes=["70","14","3","2","3"];
var colligateMap={};
for(var i=0;i<=colligateTypes_q.length;i++){
    colligateMap[colligateTypes_q[i]]=colligateTimes[i];
}
for(var i=0;i<=colligateTypes_z.length;i++){
    colligateMap[colligateTypes_z[i]]=colligateTimes[i];
}
for(var i=0;i<=colligateTypes_h.length;i++){
    colligateMap[colligateTypes_h[i]]=colligateTimes[i];
}
var controls = document.getElementById("segmentedControls");
var contents = document.getElementById("segmentedControlContents");
var html = [];
var i = 1,
    j = 1,
    m = 7, //左侧选项卡数量+1
    n = 15; //每个选项卡列表数量+1
for (; i < m; i++) {
    html.push('<a class="mui-control-item" data-index="' + (i-1) + '" href="#content' + i + '">' + controlNames[i] + '</a>');
}
html.push('<div class="mui-content-padded" style="text-align: center;">');
html.push('<button id="betsave" type="button" class="mui-btn mui-btn-primary">保存</button>');
html.push('</div>');
html.push('<div class="mui-content-padded" style="text-align: center;">');
html.push('<button id="betreset" type="button" style="margin-top:5px;" class="mui-btn mui-btn-primary">重置</button>');
html.push('</div>');
html.push('<div class="mui-content-padded" style="text-align: center;">');
html.push('<span style="margin-top:5px;">剩余分</span>');
html.push('</div>');
html.push('<div class="mui-content-padded" style="text-align: center;">');
html.push('<span id="betpoints" class="mui-badge mui-badge-danger">450000</span>');
html.push('</div>');
controls.innerHTML = html.join('');
html = [];
for (i = 1; i < m-1; i++) {
    html.push('<div id="content' + i + '" class="mui-control-content"><ul class="mui-table-view">');
    html.push('<form id="betform'+i+'" class="mui-input-group">');
    html.push('<div class="mui-row">');
    for (j=1;j<n;j++) {
        html.push('<div class="mui-input-row">');
        html.push('<label style="width: 24%">'+contentNames[j]+'</label>');
        html.push('<label style="width: 24%">'+contentTimes[j]+'倍</label>');
        html.push('<div class="mui-numbox" data-numbox-step="10" data-numbox-min="0" data-numbox-max="1000">');
        html.push('<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>');
        html.push('<input class="mui-input-numbox" type="number" name="'+contentTypes[j]+'" />');
        html.push('<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>');
        html.push('</div>');
        // html.push('<input type="text" name="'+contentTypes[j]+'" placeholder="'+contentTimes[j]+'倍">');
        html.push('</div>');
    }
    html.push('</div>');
    html.push('</form>');
    html.push('</ul></div>');

}
html.push('<div id="content6" class="mui-control-content"><ul class="mui-table-view">');

html.push('<div class="mui-row">');

html.push('<form id="betform6" class="mui-input-group">');

for(var i=0;i<7;i++){
    html.push('<div class="mui-input-row">');
    html.push('<label style="width: 24%">'+sumNames[i]+'</label>');
    html.push('<label style="width: 24%">'+sumTimes[i]+'倍</label>');
    html.push('<div class="mui-numbox" data-numbox-step="10" data-numbox-min="0" data-numbox-max="1000">');
    html.push('<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>');
    html.push('<input class="mui-input-numbox" type="number" name="'+sumTypes[i]+'" />');
    html.push('<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>');
    html.push('</div>');
    // html.push('<input type="text" name="'+sumTypes[i]+'" placeholder="'+sumTimes[i]+'倍">');
    html.push('</div>');
}
html.push('</form>');

html.push('<form class="mui-input-group">');
html.push('<div class="mui-input-row mui-radio">');
html.push('<label>前三</label>');
html.push('<input id="q3" type="radio" name="qzh" checked>');
html.push('</div>');
html.push('<div class="mui-input-row mui-radio">');
html.push('<label>中三</label>');
html.push('<input id="z3" type="radio" name="qzh">');
html.push('</div>');
html.push('<div class="mui-input-row mui-radio">');
html.push('<label>后三</label>');
html.push('<input id="h3" type="radio" name="qzh">');
html.push('</div>');
html.push('</form>');

html.push('<form id="betform7" class="mui-input-group">');
for (var l=0;l<5;l++) {
    html.push('<div class="mui-input-row bet-q3">');
    html.push('<label style="width: 24%">'+colligateNames[l]+'</label>');
    html.push('<label style="width: 24%">'+colligateTimes[l]+'倍</label>');
    html.push('<div class="mui-numbox" data-numbox-step="10" data-numbox-min="0" data-numbox-max="1000">');
    html.push('<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>');
    html.push('<input class="mui-input-numbox" type="number" name="'+colligateTypes_q[l]+'" />');
    html.push('<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>');
    html.push('</div>');
    // html.push('<input type="text" name="'+colligateTypes_q[l]+'" placeholder="'+colligateTimes[l]+'倍">');
    html.push('</div>');
}
for (var l=0;l<5;l++) {
    html.push('<div class="mui-input-row bet-z3">');
    html.push('<label style="width: 24%">'+colligateNames[l]+'</label>');
    html.push('<label style="width: 24%">'+colligateTimes[l]+'倍</label>');
    html.push('<div class="mui-numbox" data-numbox-step="10" data-numbox-min="0" data-numbox-max="1000">');
    html.push('<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>');
    html.push('<input class="mui-input-numbox" type="number" name="'+colligateTypes_z[l]+'" />');
    html.push('<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>');
    html.push('</div>');
    // html.push('<input type="text" name="'+colligateTypes_z[l]+'" placeholder="'+colligateTimes[l]+'倍">');
    html.push('</div>');
}
for (var l=0;l<5;l++) {
    html.push('<div class="mui-input-row bet-h3">');
    html.push('<label style="width: 24%">'+colligateNames[l]+'</label>');
    html.push('<label style="width: 24%">'+colligateTimes[l]+'倍</label>');
    html.push('<div class="mui-numbox" data-numbox-step="10" data-numbox-min="0" data-numbox-max="1000">');
    html.push('<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>');
    html.push('<input class="mui-input-numbox" type="number" name="'+colligateTypes_h[l]+'" />');
    html.push('<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>');
    html.push('</div>');
    // html.push('<input type="text" name="'+colligateTypes_h[l]+'" placeholder="'+colligateTimes[l]+'倍">');
    html.push('</div>');
}
html.push('</form>');

html.push('</div>');

html.push('</ul></div>');

contents.innerHTML = html.join('');
//默认选中第一个
controls.querySelector('.mui-control-item').classList.add('mui-active');
//			contents.querySelector('.mui-control-content').classList.add('mui-active');
(function() {
    var controlsElem = document.getElementById("segmentedControls");
    var contentsElem = document.getElementById("segmentedControlContents");
    var controlListElem = controlsElem.querySelectorAll('.mui-control-item');
    var contentListElem = contentsElem.querySelectorAll('.mui-control-content');
    var controlWrapperElem = controlsElem.parentNode;
    var controlWrapperHeight = controlWrapperElem.offsetHeight;
    var controlMaxScroll = controlWrapperElem.scrollHeight - controlWrapperHeight;//左侧类别最大可滚动高度
    var maxScroll = contentsElem.scrollHeight - contentsElem.offsetHeight;//右侧内容最大可滚动高度
    var controlHeight = controlListElem[0].offsetHeight;//左侧类别每一项的高度
    var controlTops = []; //存储control的scrollTop值
    var contentTops = [0]; //存储content的scrollTop值
    var length = contentListElem.length;
    for (var i = 0; i < length; i++) {
        controlTops.push(controlListElem[i].offsetTop + controlHeight);
    }
    for (var i = 1; i < length; i++) {
        var offsetTop = contentListElem[i].offsetTop;
        if (offsetTop + 100 >= maxScroll) {
            var height = Math.max(offsetTop + 100 - maxScroll, 100);
            var totalHeight = 0;
            var heights = [];
            for (var j = i; j < length; j++) {
                var offsetHeight = contentListElem[j].offsetHeight;
                totalHeight += offsetHeight;
                heights.push(totalHeight);
            }
            for (var m = 0, len = heights.length; m < len; m++) {
                contentTops.push(parseInt(maxScroll - (height - heights[m] / totalHeight * height)));
            }
            break;
        } else {
            contentTops.push(parseInt(offsetTop));
        }
    }
    contentsElem.addEventListener('scroll', function() {
        var scrollTop = contentsElem.scrollTop;
        for (var i = 0; i < length; i++) {
            var offsetTop = contentTops[i];
            var offset = Math.abs(offsetTop - scrollTop);
//						console.log("i:"+i+",scrollTop:"+scrollTop+",offsetTop:"+offsetTop+",offset:"+offset);
            if (scrollTop < offsetTop) {
                if (scrollTop >= maxScroll) {
                    onScroll(length - 1);
                } else {
                    onScroll(i - 1);
                }
                break;
            } else if (offset < 20) {
                onScroll(i);
                break;
            }else if(scrollTop >= maxScroll){
                onScroll(length - 1);
                break;
            }
        }
    });
    var lastIndex = 0;
    //监听content滚动
    var onScroll = function(index) {
        if (lastIndex !== index) {
            lastIndex = index;
            var lastActiveElem = controlsElem.querySelector('.mui-active');
            lastActiveElem && (lastActiveElem.classList.remove('mui-active'));
            var currentElem = controlsElem.querySelector('.mui-control-item:nth-child(' + (index + 1) + ')');
            currentElem.classList.add('mui-active');
            //简单处理左侧分类滚动，要么滚动到底，要么滚动到顶
            var controlScrollTop = controlWrapperElem.scrollTop;
            if (controlScrollTop + controlWrapperHeight < controlTops[index]) {
                controlWrapperElem.scrollTop = controlMaxScroll;
            } else if (controlScrollTop > controlTops[index] - controlHeight) {
                controlWrapperElem.scrollTop = 0;
            }
        }
    };
    //滚动到指定content
    var scrollTo = function(index) {
        contentsElem.scrollTop = contentTops[index];
    };
    mui(controlsElem).on('tap', '.mui-control-item', function(e) {
        $(":input").blur();
        scrollTo(this.getAttribute('data-index'));
        return false;
    });
})();
function getFormData(id) {
    var data = {};
    $("#"+id).serializeArray().map(function(x){
        if(x.value!=""){
            data[x.name] = x.value;
        }
    });

    return data;
};
document.getElementById("q3").addEventListener("tap",function (e) {
    $(".bet-q3").show();
    $(".bet-z3").hide();
    $(".bet-h3").hide();

})
document.getElementById("z3").addEventListener("tap",function (e) {
    $(".bet-q3").hide();
    $(".bet-z3").show();
    $(".bet-h3").hide();

})
document.getElementById("h3").addEventListener("tap",function (e) {
    $(".bet-q3").hide();
    $(".bet-z3").hide();
    $(".bet-h3").show();

})
document.getElementById("betsave").addEventListener('tap',function(e) {
    $(":input").blur();
    var drawtotla="";
    var datar=[];
    var data1=getFormData("betform1");
    var key;
    for(key in data1){
        if(data1[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=1;
        row.bettype=key;
        row.bettypename=codeNames[key];
        row.cost=data1[key];
        row.rate=contentMap[key];
        datar.push(row);
    }
    var data2=getFormData("betform2");
    for(key in data2){
        if(data2[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=2;
        row.bettype=key;
        row.bettypename=codeNames[key];
        row.cost=data2[key];
        row.rate=contentMap[key];
        datar.push(row);
    }
    var data3=getFormData("betform3");
    for(key in data3){
        if(data3[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=3;
        row.bettypename=codeNames[key];
        row.bettype=key;
        row.cost=data3[key];
        row.rate=contentMap[key];
        datar.push(row);
    }
    var data4=getFormData("betform4");
    for(key in data4){
        if(data4[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=4;
        row.bettypename=codeNames[key];
        row.bettype=key;
        row.cost=data4[key];
        row.rate=contentMap[key];
        datar.push(row);
    }
    var data5=getFormData("betform5");
    for(key in data5){
        if(data5[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=5;
        row.bettypename=codeNames[key];
        row.bettype=key;
        row.cost=data5[key];
        row.rate=contentMap[key];
        datar.push(row);
    }
    var data6=getFormData("betform6");
    for(key in data6){
        if(data6[key]==0){
            continue;
        }
        var row={};
        row.ballnumber=6;
        row.bettypename=codeNames[key];
        row.bettype=key;
        row.cost=data6[key];
        row.rate=sumMap[key];
        datar.push(row);
    }
    var data7=getFormData("betform7");
    for(key in data7){
        if (data7[key] == 0) {
            continue;
        }
        var row={};
        row.ballnumber=6;
        row.bettypename=codeNames[key];
        row.bettype=key;
        row.cost=data7[key];
        row.rate=colligateMap[key];
        datar.push(row);
    };
    for (var i=0;i<datar.length;i++){
        var row=datar[i];
        if(row.ballnumber==1){
            drawtotla+="一球  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }else
        if(row.ballnumber==2){
            drawtotla+="二球  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }else
        if(row.ballnumber==3){
            drawtotla+="三球  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }else
        if(row.ballnumber==4){
            drawtotla+="四球  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }else
        if(row.ballnumber==5){
            drawtotla+="五球  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }else
        if(row.ballnumber==6){
            drawtotla+="综合、龙虎  ";
            drawtotla+=row.bettypename+"    ";
            drawtotla+=row.cost+" | ";
        }
    }
    // alert(drawtotla.substring(0,drawtotla.length-3));
    var btnArray = ['否', '是'];
    mui.confirm(drawtotla.substring(0,drawtotla.length-3), '时时彩', btnArray, function(e) {
        if (e.index == 1) {//确定
            var url=localStorage.getItem("serverPath")+"/betorder/saveBetOrder.shtml";
            $.post(url,{betordersstr:JSON.stringify(datar),userid:localStorage.getItem("userid")},function(data){
                mui.alert(data.info, '时时彩');
            },"json");
        } else {
            mui.alert("已经取消！", '时时彩');
        }
    })

});
document.getElementById("betreset").addEventListener('tap', function() {
    $(":input").blur();
    var forms=document.getElementsByTagName("form");
    for(var i=0;i<forms.length;i++){
        var form=forms[i];
        form.reset();
    }
});
mui.ready(
    function () {
        var forms=document.getElementsByTagName("form");
        for(var i=0;i<forms.length;i++){
            var form=forms[i];
            form.reset();
        }
    }
)