let api = [];
const apiDocListSize = 6
api.push({
    name: 'default',
    order: '1',
    list: []
})
api.push({
    name: '平台通用接口API',
    order: '2',
    list: []
})
api[1].list.push({
    alias: 'ApiStatusMonitorController',
    order: '1',
    link: '平台当前监控状态',
    desc: '平台当前监控状态',
    list: []
})
api[1].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/stage/getStatus.do',
    desc: '每次发版前，将版本号进行调整，以明确当前发布的版本和启动的版本是一致的',
});
api.push({
    name: '一体化平台接口API',
    order: '3',
    list: []
})
api.push({
    name: '小览生活接口API',
    order: '4',
    list: []
})
api[3].list.push({
    alias: 'CommBookController',
    order: '1',
    link: '公共记账本读写控制类',
    desc: '公共记账本读写控制类',
    list: []
})
api[3].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/book/list.do',
    desc: '获取公共记账本列表或分页,返回CommBook对象.',
});
api[3].list[0].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/book/{id}.do',
    desc: '主键获取公共记账本详细信息.',
});
api[3].list[0].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/book/save.do',
    desc: '新增公共记账本数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[0].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/book/update.do',
    desc: '修改公共记账本数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[0].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/book/delete/{ids}.do',
    desc: '删除公共记账本数据操作逻辑，根据操作类型，执行删除操作.',
});
api[3].list.push({
    alias: 'WechatMiniController',
    order: '2',
    link: '微信小程序接口相关控制器',
    desc: '微信小程序接口相关控制器',
    list: []
})
api[3].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmini/getCode.do',
    desc: '获取微信扫码登录验证码',
});
api[3].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmini/isLogin/{loginCode}.do',
    desc: '获取微信扫码是否已登录',
});
api[3].list[1].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmini/login/{code}/{mobile}.do',
    desc: '微信小程序登录',
});
api[3].list.push({
    alias: 'WechatOpenController',
    order: '3',
    link: '微信开放平台相关控制器',
    desc: '微信开放平台相关控制器',
    list: []
})
api[3].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wechat/.do',
    desc: '微信公众号token验证',
});
api[3].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wechat/.do',
    desc: '微信公众号消息对接',
});
api[3].list.push({
    alias: 'WechatMpController',
    order: '4',
    link: '微信服务号公众号相关控制器',
    desc: '微信服务号公众号相关控制器',
    list: []
})
api[3].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmp/getRedirect.do',
    desc: '微信公众号获取oauth2完整授权地址',
});
api[3].list[3].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmp/login.do',
    desc: '微信公众号oauth2授权后获取用户信息',
});
api[3].list[3].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/open/wxmp/getJsapiTicket.do',
    desc: '微信公众号页面端原生能力调用',
});
api[3].list.push({
    alias: 'MindRecordController',
    order: '5',
    link: '心愿提醒任务记录读写控制类',
    desc: '心愿提醒任务记录读写控制类',
    list: []
})
api[3].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/mindrecord/list.do',
    desc: '获取心愿提醒任务记录列表或分页,返回MindRecord对象.',
});
api[3].list[4].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/mindrecord/{id}.do',
    desc: '主键获取心愿提醒任务记录详细信息.',
});
api[3].list[4].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/mindrecord/save.do',
    desc: '新增心愿提醒任务记录数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[4].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/mindrecord/update.do',
    desc: '修改心愿提醒任务记录数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[4].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/mindrecord/delete/{ids}.do',
    desc: '删除心愿提醒任务记录数据操作逻辑，根据操作类型，执行删除操作.',
});
api[3].list.push({
    alias: 'WishInfoController',
    order: '6',
    link: '心愿目标池读写控制类',
    desc: '心愿目标池读写控制类',
    list: []
})
api[3].list[5].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/wishinfo/list.do',
    desc: '获取心愿目标池列表或分页,返回WishInfo对象.',
});
api[3].list[5].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/wishinfo/{id}.do',
    desc: '主键获取心愿目标池详细信息.',
});
api[3].list[5].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/wishinfo/save.do',
    desc: '新增心愿目标池数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[5].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/wishinfo/update.do',
    desc: '修改心愿目标池数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[5].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/wishinfo/delete/{ids}.do',
    desc: '删除心愿目标池数据操作逻辑，根据操作类型，执行删除操作.',
});
api[3].list.push({
    alias: 'CommCategoryController',
    order: '7',
    link: '提醒分类信息读写控制类',
    desc: '提醒分类信息读写控制类',
    list: []
})
api[3].list[6].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/category/list.do',
    desc: '获取提醒分类信息列表或分页,返回CommCategory对象.',
});
api[3].list[6].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/category/{id}.do',
    desc: '主键获取提醒分类信息详细信息.',
});
api[3].list[6].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/category/save.do',
    desc: '新增提醒分类信息数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[6].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/category/update.do',
    desc: '修改提醒分类信息数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[6].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/comm/category/delete/{ids}.do',
    desc: '删除提醒分类信息数据操作逻辑，根据操作类型，执行删除操作.',
});
api[3].list.push({
    alias: 'CheckRecordController',
    order: '8',
    link: '记账打卡记录读写控制类',
    desc: '记账打卡记录读写控制类',
    list: []
})
api[3].list[7].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/checkrecord/list.do',
    desc: '获取记账打卡记录列表或分页,返回CheckRecord对象.',
});
api[3].list[7].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/checkrecord/{id}.do',
    desc: '主键获取记账打卡记录详细信息.',
});
api[3].list[7].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/checkrecord/save.do',
    desc: '新增记账打卡记录数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[7].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/checkrecord/update.do',
    desc: '修改记账打卡记录数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[7].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/checkrecord/delete/{ids}.do',
    desc: '删除记账打卡记录数据操作逻辑，根据操作类型，执行删除操作.',
});
api[3].list.push({
    alias: 'BookInfoController',
    order: '9',
    link: '记账本台账读写控制类',
    desc: '记账本台账读写控制类',
    list: []
})
api[3].list[8].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/bookinfo/list.do',
    desc: '获取记账本台账列表或分页,返回BookInfo对象.',
});
api[3].list[8].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/bookinfo/{id}.do',
    desc: '主键获取记账本台账详细信息.',
});
api[3].list[8].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/bookinfo/save.do',
    desc: '新增记账本台账数据操作逻辑，根据操作类型，执行新增操作.',
});
api[3].list[8].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/bookinfo/update.do',
    desc: '修改记账本台账数据操作逻辑，根据操作类型，执行修改操作.',
});
api[3].list[8].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://linlan.net/xiaolan_backend/api/my/bookinfo/delete/{ids}.do',
    desc: '删除记账本台账数据操作逻辑，根据操作类型，执行删除操作.',
});
api.push({
    name: '错误码列表',
    order: '5',
    list: []
})
api.push({
    name: '数据字典',
    order: '6',
    list: []
})
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    let doc;
    if (apiGroups.length > 0) {
         if (apiDocListSize == 1) {
            let apiData = apiGroups[0].list;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated == 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].href + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#_'+apiGroup.order+'_'+ apiData[j].order + '_'+ apiData[j].link + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated == 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#_'+apiGroup.order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}