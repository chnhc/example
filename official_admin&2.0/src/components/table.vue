<template>
  <div class="table" :style="{height:mHeight}">

    <div class="opt_all">
      <div style="  display: inline-block; margin-right:20px">
        <el-button type="primary" size="small" >选中详情</el-button>
      </div>
      <div style="display: inline-block; position: absolute; bottom:1px; ">
        <Input v-model="value13">
             <Select v-model="select3" slot="prepend" style="width: 80px">
                 <Option value="day">日期</Option>
                 <Option value="name">姓名</Option>
                 <Option value="pro">省份</Option>
                 <Option value="city">市区</Option>
                 <Option value="address">地址</Option>

             </Select>
         </Input>

      </div>
      <div  class="opt_right">


        <el-button-group style="margin-right:10px;">


          <el-popover
            placement="left-start"
            width="100"
            trigger="click">

            <CheckboxGroup v-model="check" >
                <Checkbox  class="opt_checkbox" label="日期"></Checkbox>
                <Checkbox  class="opt_checkbox" label="姓名"></Checkbox>
                <Checkbox  class="opt_checkbox" label="省份"></Checkbox>
                <Checkbox  class="opt_checkbox" label="市区"></Checkbox>
                <Checkbox  class="opt_checkbox" label="地址"></Checkbox>
            </CheckboxGroup>

            <el-button  size="small" type="primary" icon="el-icon-menu" slot="reference"></el-button>
          </el-popover>


        </el-button-group>

        <el-button-group style="margin-right:10px;">

          <el-tooltip content="导出选中数据" placement="bottom">
              <el-button size="small" type="primary" icon="el-icon-document"></el-button>
          </el-tooltip>

          <el-tooltip content="删除选中数据 " placement="bottom">
              <el-button size="small" type="warning" icon="el-icon-delete"></el-button>
          </el-tooltip>

        </el-button-group>

        <el-button-group>

          <el-tooltip content="下载所有数据 " placement="bottom">
              <el-button size="small" type="success" icon="el-icon-download"></el-button>
          </el-tooltip>

          <el-tooltip content="打印" placement="bottom">
              <el-button size="small" type="success" icon="el-icon-printer"></el-button>
          </el-tooltip>

          <el-tooltip content="删除所有数据 " placement="bottom">
              <el-button size="small" type="danger" icon="el-icon-delete"></el-button>
          </el-tooltip>

        </el-button-group>
      </div>

    </div>

    <el-table
       :data="tableData"
       style="width: 100%"
       border
       height="100%"
       @sort-change="sortChange"
       :default-sort = "{prop: 'date'}">
       <el-table-column
         type="selection"
         width="55">
       </el-table-column>
       <el-table-column
         fixed
         prop="date"
         sortable
         label="日期"
         width="150">
       </el-table-column>
       <el-table-column
         prop="name"
         label="姓名"
         width="120">
       </el-table-column>
       <el-table-column
         prop="province"
         label="省份"
         width="120">
       </el-table-column>
       <el-table-column
         prop="city"
         label="市区"
         width="120">
       </el-table-column>
       <el-table-column
         prop="address"
         label="地址"
         width="300">
       </el-table-column>
       <el-table-column
         prop="name"
         label="姓名"
         width="120">
       </el-table-column>
       <el-table-column
         prop="province"
         label="省份"
         width="120">
       </el-table-column>
       <el-table-column
         prop="city"
         label="市区"
         width="120">
       </el-table-column>
       <el-table-column
         prop="address"
         label="地址"
         width="300">
       </el-table-column>
       <el-table-column
        fixed="right"
         prop="zip"
         label="邮编"
         width="120">
       </el-table-column>
     </el-table>

     <div class="block">

        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
        </el-pagination>
      </div>

  </div>
</template>

<script>
  export default {
    props: {
      mHeight: {
        type: String,
        default: "1000px"
      }
    },
    data () {
        return {
          check: ['日期', '姓名', '省份', '市区', '地址'],
          vertical: 'apple',
          value13: '',
          select3: 'day',
          columns2: [
                  {
                      title: 'Name',
                      key: 'name',
                      width: 100,
                      fixed: 'left'
                  },
                  {
                      title: 'Age',
                      key: 'age',
                      width: 100
                  },
                  {
                      title: 'Province',
                      key: 'province',
                      width: 100
                  },
                  {
                      title: 'City',
                      key: 'city',
                      width: 100
                  },
                  {
                      title: 'Address',
                      key: 'address',
                      width: 200
                  },
                  {
                      title: 'Postcode',
                      key: 'zip',
                      width: 100
                  },
                  {
                      title: 'Action',
                      key: 'action',
                      fixed: 'right',
                      width: 120,
                      render: (h, params) => {
                          return h('div', [
                              h('Button', {
                                  props: {
                                      type: 'text',
                                      size: 'small'
                                  }
                              }, 'View'),
                              h('Button', {
                                  props: {
                                      type: 'text',
                                      size: 'small'
                                  }
                              }, 'Edit')
                          ]);
                      }
                  }
              ],
              data4: [
                  {
                      name: 'John Brown',
                      age: 18,
                      address: 'New York No. 1 Lake Park',
                      province: 'America',
                      city: 'New York',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  },
                  {
                      name: 'John Brown',
                      age: 18,
                      address: 'New York No. 1 Lake Park',
                      province: 'America',
                      city: 'New York',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  },
                  {
                      name: 'John Brown',
                      age: 18,
                      address: 'New York No. 1 Lake Park',
                      province: 'America',
                      city: 'New York',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  },
                  {
                      name: 'John Brown',
                      age: 18,
                      address: 'New York No. 1 Lake Park',
                      province: 'America',
                      city: 'New York',
                      zip: 100000
                  },
                  {
                      name: 'Jim Green',
                      age: 24,
                      address: 'Washington, D.C. No. 1 Lake Park',
                      province: 'America',
                      city: 'Washington, D.C.',
                      zip: 100000
                  },
                  {
                      name: 'Joe Black',
                      age: 30,
                      address: 'Sydney No. 1 Lake Park',
                      province: 'Australian',
                      city: 'Sydney',
                      zip: 100000
                  },
                  {
                      name: 'Jon Snow',
                      age: 26,
                      address: 'Ottawa No. 2 Lake Park',
                      province: 'Canada',
                      city: 'Ottawa',
                      zip: 100000
                  }
              ]
            ,
          tableData: [{
            date: '2016-05-03',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '32321312虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            name: '王小虎11',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }]

        }
    },
    methods:{
      sortChange(){
        console.log("aa");
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.table{
  position: relative;
  height: 200px;
  overflow: hidden;
  padding: 10px 20px 100px 20px;
}
.block{
  margin-top: 10px;

}
.opt_all{
  position: relative;
  margin-bottom: 10px;
  width: 100%;
}
.opt_right{
  display: inline-block;
  position: absolute;
  right: 10px;
}
.opt_checkbox{
  display: block;
  margin-top: 5px;
}

</style>
