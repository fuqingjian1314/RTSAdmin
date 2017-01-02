$(function() {
	
	var html="<a herf='#' class='udf_addtag' onclick='purepop(\" \",\" #financing-classily\")' >【+添加标签】</a>"
	
	        var defaultData = [
	          {
	            text: '贷款品种  ',        
	            tags: ['4'],
	            nodes: [
	              {
	                text: '贷款品种1'+html,
	                tags: ['2'],
	                nodes: [
	                  {
	                    text: '贷款品种1-1',
	                    tags: ['0']
	                  },
	                  {
	                    text: '贷款品种1-2',
	                    tags: ['0']
	                  }
	                ]
	              },	              
	            ]
	          },
	          {
	            text: '国土情况   ',
	            tags: ['0']
	          },
	          {
	            text: '国土性质   ',
	             tags: ['0']
	          },
	          {
	            text: '房屋区域   ',
	            tags: ['0']
	          },
	          {
	            text: '个人收入   ',
	            tags: ['0']
	          }
	        ];
	        $('#treeview,#treeview1').treeview({
	        	
	          selectedColor: '#333',
	          selectedBackColor: 'none',
	          data: defaultData,
	        });//树形结构数据	
	    
			
		});