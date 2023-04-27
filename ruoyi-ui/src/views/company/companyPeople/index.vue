<template>
  <div class="app-container">
    <el-form ref="queryParams" :model="queryParams" :rules="rules" label-width="80px"  v-show="showSearch">
      <el-row>
        <el-col :span="6">
          <el-form-item label="员工编号" prop="ygbh">
            <el-input v-model="queryParams.ygbh" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="员工姓名" prop="ygxm">
            <el-input v-model="queryParams.ygxm" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="员工生日" prop="ygsr">
            <el-input v-model="queryParams.ygsr" maxlength="30" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="员工户籍" prop="yghj">
            <el-input v-model="queryParams.yghj" maxlength="100" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="工作内容" prop="gznr">
            <el-input v-model="queryParams.gznr" maxlength="100" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <div style="text-align: center">
        <el-form-item>
          <el-button type="cyan" size="mini" icon="el-icon-search" @click="handleQuery">搜索</el-button>
          <el-button type="danger" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['queryParams:queryParamsInfo:add']">新增</el-button>
        </el-form-item>
      </div>
    </el-form>
    <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    <el-table v-loading="loading" :data="peopleInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="员工编号" prop="ygbh" width="160" align="center"/>
      <el-table-column label="员工姓名" prop="ygxm" width="160" align="center"/>
      <el-table-column label="员工生日" prop="ygsr" :show-overflow-tooltip="true" width="160" align="center"/>
      <el-table-column label="员工户籍" prop="yghj" :show-overflow-tooltip="true" width="160" align="center"/>
      <el-table-column label="工作内容" prop="gznr" width="160" align="center"/>
      <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['goods:goodsInfo:update']">修改</el-button>
          <!--<el-button size="mini" type="text" icon="el-icon-plus" @click="handleAdd(scope.row)" v-hasPermi="['system:menu:add']">新增</el-button>-->
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['goods:goodsInfo:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品信息对话框。 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="员工编号" prop="ygbh">
              <el-input v-model="form.ygbh" :readonly="readonly" placeholder="请输入商品编号" />
            </el-form-item>
            <el-form-item label="员工姓名" prop="ygxm">
              <el-input v-model="form.ygxm" placeholder="请输入商品名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="员工生日" prop="ygsr">
              <el-input v-model="form.ygsr" placeholder="请输入商品属性" />
            </el-form-item>
            <el-form-item label="员工户籍" prop="yghj">
              <el-input v-model="form.yghj" placeholder="请输入商品价格" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作内容" prop="gznr">
              <el-input v-model="form.gznr" placeholder="请输入商品属性" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {selectlistPeopleInfo,addPeople,delPeople,updatePeople} from "@/api/company/peopleInfo";
  export default {
    name: "companyPeople",
    data() {
      return {
        //表格高度
        //   :450,
        //修改时设置为只读
        readonly:true,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 是否显示弹出层
        open: false,
        //判断商品编号是否已经存在，1为可以输入（表中不存在），0为不可输入（表中存在）
        ifygbhUnique: '0',
        // 显示搜索条件
        showSearch: true,
        // 菜单表格树数据
        menuList: [],
        //要导出的报表的list
        exportList:[],
        // 商品信息表格数据
        peopleInfoList: [],
        //总条数
        total: 0,
        // 默认密码
        initPassword: undefined,
        // 菜单树选项
        menuOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 显示状态数据字典
        visibleOptions: [],
        // 菜单状态数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          ygbh: undefined,
          ygxm: undefined,
          ygxb: undefined,
          ygsr: undefined,
          yghj: undefined,
          gznr: undefined
        },
        //增加一条数据时检查是否存在相同的商品编号
        checkqueryParams: {
          pageNum: 1,
          pageSize: 10,
          ygbh: undefined,
          ygxm: undefined,
          ygxb: undefined,
          ygsr: undefined,
          yghj: undefined,
          gznr: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          ygbh1: [
            { required: true, message: "商品编号不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.ygbh)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 查询菜单列表 */
      getList() {
        this.loading = true;
        selectlistPeopleInfo(this.queryParams).then(response => {
          this.peopleInfoList = response.rows;
          this.total=response.total;
          this.loading = false;
        });
      },
      /**检查是否已经存在商品编号*/
      checkygbhUnique(name){
        if(this.form.ygbh!='' && this.form.ygbh!=null){
          this.checkqueryParams.ygbh = name;
          selectlistPeopleInfo(this.checkqueryParams).then(response=>{
            this.ifygbhUnique=response.data;
            if(response.total==0){
              this.$message.error({
                content:'表中已经存在该编号，不可重复取值，请另取他值',
                duration:5,
              });
              this.form.ygbh='';
            }
          })
        }
      },
      /** 转换菜单数据结构 */
      normalizer(node) {
        if (node.children && !node.children.length) {
          delete node.children;
        }
        return {
          id: node.menuId,
          label: node.menuName,
          children: nodeF.children
        };
      },
      /** 查询菜单下拉树结构 */
      getTreeselect() {
        listMenu().then(response => {
          this.menuOptions = [];
          const menu = { menuId: 0, menuName: '主类目', children: [] };
          menu.children = this.handleTree(response.data, "menuId");
          this.menuOptions.push(menu);
        });
      },
      // 显示状态字典翻译
      visibleFormat(row, column) {
        if (row.menuType == "F") {
          return "";
        }
        return this.selectDictLabel(this.visibleOptions, row.visible);
      },
      // 菜单状态字典翻译
      statusFormat(row, column) {
        if (row.menuType == "F") {
          return "";
        }
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          ygbh: undefined,
          ygxm: undefined,
          ygxb: undefined,
          ygsr: undefined,
          yghj: undefined,
          gznr: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum=1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryParams");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd(row) {
        this.reset();
        this.readonly=false;
        this.open = true;
        this.title = "添加商品信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.form=row;
        this.form.ygbh1=row.ygbh;
        this.readonly=true;
        this.open = true;
        this.title = "修改商品信息";
        // getGoods(row.ygbh).then(response => {
        //   this.form = response.data;
        //
        // });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.ygbh = this.form.ygbh1;
            if(this.readonly == true){
              updateGoods(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open=false;
                this.getList();
              });
            }else{
              addGoods(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除名称为"' + row.spmc + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGoods(row.ygbh);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      }
    }
  };
</script>

<style scoped>

</style>

