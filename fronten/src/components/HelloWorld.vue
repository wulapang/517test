<template>
  <div>
      <el-form :inline="true" class="demo-form-inline">
          <el-form-item>
            <el-input
              v-model="search.empName"
              class="search_name"
              size="mini"
              placeholder="输入姓名查询">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-select clearable  size="mini" class="search_name" v-model="search.deptName" placeholder="请选择">
              <el-option label="业务部" value="业务部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
              <el-option label="人事部" value="人事部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-select clearable size="mini" class="search_name" v-model="search.empDegreeName" placeholder="请选择">
              <el-option label="大专" value="大专"></el-option>
              <el-option label="本科" value="本科"></el-option>
              <el-option label="研究生" value="研究生"></el-option>
              <el-option label="博士" value="博士"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              type="text"
              @click="onSearch()"
              class="el-icon-search">查询
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              class="el-icon-refresh"
              type="text"
              @click="refreshData">刷新
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              class="el-icon-circle-plus-outline"
              type="text"
              @click="dialogVisible = true">添加
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              class="el-icon-circle-plus-outline"
              type="text"
              @click="importexcel()">导出所有用户信息
            </el-button>
          </el-form-item>
      </el-form>
      <el-table
        :data="tableData"
        highlight-current-row
        border
        style="width: 100%">
        <el-table-column prop="id" label="编号" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="empName" label="员工姓名" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="age" label="年龄" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="sex" label="性别" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="deptName" label="部门" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="empDegreeName" label="学历" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column
          label="操作"
          fixed="right"
          width="200">
          <template slot-scope="scope">
            <el-button
              size="mini"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
        <el-dialog
          title="添加"
          :append-to-body='true'
          :visible.sync="dialogVisible"
          width="500px"
          :before-close="handleClose">
          <el-input type="hidden" v-model="ruleForm.id"/>
          <el-form-item label="姓名" prop="empName">
            <el-input v-model="ruleForm.empName"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="ruleForm.age"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="ruleForm.sex" placeholder="请选择">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="部门" prop="deptName">
            <el-select v-model="ruleForm.deptName" placeholder="请选择">
              <el-option label="业务部" value="业务部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
              <el-option label="人事部" value="人事部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学历" prop="empDegreeName">
            <el-select v-model="ruleForm.empDegreeName" placeholder="请选择">
              <el-option label="大专" value="大专"></el-option>
              <el-option label="本科" value="本科"></el-option>
              <el-option label="研究生" value="研究生"></el-option>
              <el-option label="博士" value="博士"></el-option>
            </el-select>
          </el-form-item>
          <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="addUser()" type="primary" size="medium">确 定</el-button>
          </span>
        </el-dialog>
      </el-form>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogUpdate"
        width="500px"
        :before-close="handleClose">
        <el-input type="hidden" v-model="ruleForm.id"/>
           <el-form-item label="姓名" prop="empName">
            <el-input v-model="ruleForm.empName"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="ruleForm.age"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="ruleForm.sex" placeholder="请选择">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="部门" prop="deptName">
            <el-select v-model="ruleForm.deptName" placeholder="请选择">
              <el-option label="业务部" value="业务部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
              <el-option label="人事部" value="人事部"></el-option>
              <el-option label="后勤部" value="后勤部"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学历" prop="empDegreeName">
            <el-select v-model="ruleForm.empDegreeName" placeholder="请选择">
              <el-option label="大专" value="大专"></el-option>
              <el-option label="本科" value="本科"></el-option>
              <el-option label="研究生" value="研究生"></el-option>
              <el-option label="博士" value="博士"></el-option>
            </el-select>
          </el-form-item>
        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="updateUser()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>
      <br>
      <div class="pages">
        <el-pagination
          background
          :disabled = "disablePage"
          :current-page.sync="currentPage"
          small
          layout="prev, pager, next"
          :page-size="pageSize"
          :total="total"
          @current-change="handleCurrentChange">
        </el-pagination>
      </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    id: '',
                    empName: '',
                    deptName: '',
                    sex: '',
                    age: 20,
                    empDegreeName: ''
                },
                rules: {
                    userName: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur' }
                    ],
                    userAddress: [
                        { required: true, message: '请输入住址', trigger: 'blur' },
                        { min: 5, message: '长度大于 5 个字符', trigger: 'blur' }
                    ],
                },
                tableData: [],
                search: {
                  empName: '',
                  deptName: '',
                  empDegreeName: ''
                },
                dialogVisible: false,
                dialogUpdate: false,
                pageSize: 10,
                currentPage: 1,
                total: 0,
                disablePage: false
            }
        },
        methods: {
            importexcel() {
              this.axios({
                    method: 'get',
                    url:'/emp/importUser',
                    responseType: "blob",
              }).then(response =>{
                let filename = "用户信息.xls";
                //response.data为下载的文件数据
                let url = window.URL.createObjectURL(new Blob([response.data]));
                let link = document.createElement("a");
                link.style.display = "none";
                link.href = url;
                link.setAttribute("download", filename);
                document.body.appendChild(link);
                link.click();
              })
            },
            handleEdit(index, row) {
                this.dialogUpdate = true;
                this.ruleForm = Object.assign({}, row); //这句是关键！！！
            },
            handleDelete(index, row) {
                console.log(index, row);
                this.$confirm('删除操作, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let postData = this.qs.stringify({
                        userId: row.userId,
                    });
                    this.axios({
                        method: 'get',
                        url:'/emp/delete',
                        params : {
                          id: row.id,
                        }
                    }).then(response =>
                    {
                        this.getPages();
                        this.currentPage = 1;
                        this.axios.post('/emp/page').then(response =>
                        {
                            this.tableData = response.data;
                        }).catch(error =>
                        {
                            console.log(error);
                        });
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        console.log(response);
                    }).catch(error =>
                    {
                        console.log(error);
                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            },
            handleCurrentChange() {
                console.log(`当前页: ${this.currentPage}`);
                let postData = this.qs.stringify({
                    page: this.currentPage
                });
                this.axios({
                    method: 'post',
                    url:'/emp/page',
                    data:postData
                }).then(response =>
                {
                    this.tableData = response.data;
                }).catch(error =>
                {
                    console.log(error);
                });
            },
            cancel() {
                this.dialogUpdate = false;
                this.dialogVisible = false;
                this.emptyUserData();
            },
            emptyUserData(){
                this.ruleForm = {
                    userName: '',
                    userDate: '',
                    userAddress: ''
                }
            },
            addUser() {
              console.log(this.ruleForm)
                this.axios({
                    method: 'post',
                    url:'/emp/insert',
                    data: this.ruleForm
                }).then(response =>
                {
                    this.axios.post('/emp/page').then(response =>
                    {
                        this.tableData = response.data;
                        this.currentPage = 1;
                        this.$message({
                            type: 'success',
                            message: '已添加!'
                        });
                    }).catch(error =>
                    {
                        console.log(error);
                    });
                    this.getPages();
                    this.dialogVisible = false
                    console.log(response);
                }).catch(error =>
                {
                    console.log(error);
                });
            },
            updateUser() {
                this.axios({
                    method: 'post',
                    url:'/emp/update',
                    data: this.ruleForm
                }).then(response =>
                {
                    this.handleCurrentChange();
                    this.cancel();
                    this.$message({
                        type: 'success',
                        message: '更新成功!'
                    });
                    console.log(response);
                }).catch(error =>
                {
                    this.$message({
                        type: 'success',
                        message: '更新失败!'
                    });
                    console.log(error);
                });
            },
            onSearch() {
              this.search.currentPage = this.currentPage
                this.axios({
                    method: 'post',
                    url: '/emp/search',
                    data: this.search
                }).then(response =>
                {
                    this.tableData = response.data.data;
                    this.total = response.data.total;
                }).catch(error =>
                {
                    console.log(error);
                });
            },
            getPages() {
                this.axios.post('/emp/rows').then(response =>
                {
                    this.total = response.data;
                }).catch(error =>
                {
                    console.log(error);
                });
            },
            refreshData() {
                location.reload();
            }
        },
        created() {
            /*this.axios.get('static/user.json').then(response =>
            {
                this.tableData = response.data.tableData;
                this.total = response.data.tableData.length;
                // console.log(JSON.parse(JSON.stringify(response.data))['tableData'])
            });*/
            this.axios.post('/emp/page').then(response =>
            {
                this.tableData = response.data;
            }).catch(error =>
            {
                console.log(error);
            });

            this.axios.post('/emp/rows').then(response =>
            {
                this.total = response.data;
            }).catch(error =>
            {
                console.log(error);
            });

        },
    }
</script>
<style scoped>
  .search_name{
    width: 200px;
  }
  .pages{
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
