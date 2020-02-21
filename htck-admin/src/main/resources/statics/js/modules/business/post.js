$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'business/post/list',
        datatype: "json",
        colModel: [			
			{ label: '职位ID', name: 'postId', index: 'post_id', align:'center' , width: 50, key: true },
			{ label: '职位名称', name: 'postName', index: 'post_name',align:'center', width: 80 },
			{ label: '招聘人数', name: 'hireNumber', index: 'hire_number',align:'center', width: 80 },
            { label: '薪酬范围', name: 'salaryRange', index: 'salary_range',align:'center', width: 80 },
            { label: '岗位部门', name: 'department', index: 'department',align:'center', width: 80 },
            { label: '工作城市', name: 'workCity', index: 'work_city',align:'center', width: 80 },
			{ label: '岗位职责', name: 'postDuty', index: 'post_duty',align:'center', width: 80 },
			{ label: '岗位要求', name: 'postRequirement', index: 'post_requirement',align:'center', width: 80 },
			{ label: '投递邮箱', name: 'deliveryMail', index: 'delivery_mail', align:'center',width: 80 },
			{ label: '发布时间', name: 'publishTime', index: 'publish_time',align:'center', width: 80 ,formatter:"date",formatoptions:{newformat:'Y-m-d'}},
			{ label: '职位状态', name: 'postState', index: 'post_state',align:'center', width: 80 , formatter: "select",editoptions:{value:"0:下架;1:上架"}},
			{ label: '创建时间', name: 'createTime', index: 'create_time',align:'center', width: 80 },
			{ label: '更新时间', name: 'updateTime',index: 'update_time',align:'center', width: 80 },
            { label: '操作', name: 'operate', index: 'operate',align:'center', width: 80 ,formatter:operateType}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

// 生成操作类型按钮
function operateType(cellvalue, options, rowObject){
    var btn = '';
    if(rowObject.postState == 0){
        btn = "<input type='button' style='height:20px;background:green' value='上架' onclick=\"operate("+ rowObject.postId +",1)\"/>";
    }else {
        btn = "<input type='button' style='height:20px;background:red' value='下架' onclick=\"operate("+ rowObject.postId +",0)\"/>";
    }
    return btn;
}

// 职位上下架
function operate(postId,type) {
    var url ="business/post/operate";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        data: {postId:postId,type:type},
        success: function(r){
            if(r.code === 0){
                layer.msg("操作成功", {icon: 1});
                vm.reload();
            }else{
                layer.alert(r.msg);
            }
        }
    });
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		post: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.post = {};
		},
		update: function (event) {
			var postId = getSelectedRow();
			if(postId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(postId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.post.postId == null ? "business/post/save" : "business/post/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.post),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var postIds = getSelectedRows();
			if(postIds == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "business/post/delete",
                        contentType: "application/json",
                        data: JSON.stringify(postIds),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(postId){
			$.get(baseURL + "business/post/info/"+postId, function(r){
                vm.post = r.post;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});