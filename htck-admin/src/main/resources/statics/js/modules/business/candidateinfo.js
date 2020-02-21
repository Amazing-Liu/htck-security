$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'business/candidateinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '申请职位', name: 'postName', index: 'post_name', width: 80 },
			{ label: '申请人名字', name: 'candidateName', index: 'candidate_name', width: 80 }, 			
			{ label: '联系电话', name: 'phone', index: 'phone', width: 80 }, 			
			{ label: '邮箱', name: 'email', index: 'email', width: 80 }, 			
			{ label: '当前公司', name: 'currentCompany', index: 'current_company', width: 80 }, 			
			{ label: '当前职位', name: 'currentPost', index: 'current_post', width: 80 }, 			
			{ label: '总工作年限', name: 'workSeniority', index: 'work_seniority', width: 80 }, 			
			{ label: '学历', name: 'qualification', index: 'qualification', width: 80 }, 			
			{ label: '毕业院校', name: 'graduateSchool', index: 'graduate_school', width: 80 }, 			
			{ label: '专业', name: 'major', index: 'major', width: 80 }, 			
			// { label: '附件简历路径', name: 'curriculumVitaePath', index: 'curriculum_vitae_path', width: 80 },
			{ label: '申请日期', name: 'applyDate', index: 'apply_date', width: 80 },
            { label: '附件简历', name: 'download', index: 'download', width: 80, formatter: buildDownloadUrl }
			// { label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
			// { label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }
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


function buildDownloadUrl(cellvalue, options, rowObject){
    var url = baseURL + 'business/candidateinfo/downLoad?candidateId='+rowObject.id;

    return "<a href='"+url+"'>下载</a>";
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		candidateInfo: {},
        q: {
            postName: null
        }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.candidateInfo = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.candidateInfo.id == null ? "business/candidateinfo/save" : "business/candidateinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.candidateInfo),
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
			var ids = getSelectedRows();
			if(ids == null){
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
                        url: baseURL + "business/candidateinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get(baseURL + "business/candidateinfo/info/"+id, function(r){
                vm.candidateInfo = r.candidateInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        find: function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'postName': vm.q.postName},
                page:1
            }).trigger("reloadGrid");
        }


	}
});