<template>
  <div class="app-container">
    <p class="layout1">上海畅奕铝业发展有限公司</p>
    <p class="layout2">销售清单</p>
    <br>
    <el-form ref="queryParams" :model="queryParams" :rules="rules" label-width="80px"  v-show="showSearch">
      <el-row>
        <el-col :span="6">
          <el-form-item label="购货单位" prop="ghdw">
            <el-input v-model="queryParams.ghdw" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="联系电话" prop="lxdh">
            <el-input v-model="queryParams.lxdh" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="单据编号" prop="djbh">
            <el-input v-model="queryParams.djbh" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="单据日期" prop="djrq">
            <el-date-picker v-model="queryParams.djrq" clearable transfer/>
          </el-form-item>
        </el-col>
      </el-row>
      <div style="text-align: center">
        <el-form-item>
          <!--icon图标链接-->
          <!--https://element.eleme.cn/#/zh-CN/component/icon#tu-biao-ji-he-->
          <el-button type="primary" size="mini" icon="el-icon-search" @click="handlePrint" :loading="loading1">打印</el-button>
          <el-button type="danger" icon="el-icon-refresh" size="mini" @click="resetPrint('rules')">重置</el-button>
          <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportPrint" :loading="downloading">导出文件</el-button>
          <el-button type="default" icon="el-icon-circle-plus-outline" size="mini" @click="sumPrint" :loading="loading2">计算总价</el-button>
          <!--<el-form-item label="总金额" prop="zje">-->
            <!--<span>总金额（人民币大小）：{{inputVal | toChies}}</span>-->
          <!--</el-form-item>-->
        </el-form-item>

      </div>
    </el-form>
    <div class="tableDiv">
      <el-table :data="tableData" highlight-current-row @cell-click="cellClick" :max-height="tableHeight" ref="lsTable">
        <el-table-column v-for="(item, index) in tableColumn" :key="index" :prop="item.prop" :label="item.label" :width="item.width">
          <template slot-scope="scope">
            <el-input v-if="item.edit" size="small"
                      ref="tableInput" v-model="scope.row[item.prop]" @blur="removeClass" @change="handleEdit(item.prop, scope.$index, scope.row)"></el-input>
            <span>{{ scope.row[item.prop] }}</span>
          </template>
          <el-table-column v-for="(itemchild, indexchild) in item.children" :key="indexchild" :prop="itemchild.prop" :label="itemchild.label" :width="itemchild.width">
            <template slot-scope="scope">
              <el-input
                v-if="itemchild.edit" size="small" ref="tableInput" v-model="scope.row[itemchild.prop]" @blur="removeClass" @change="handleEdit(itemchild.prop, scope.$index, scope.row)"></el-input>
              <span>{{ scope.row[itemchild.prop] }}</span>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
      <Footer><p>注： <br>
        ✔   1.所有货物当面点清,事后概不处理。谢谢配合！<br>
        ✔   2.本单据收货人签名作未付款凭证</p><p>特别提示：退货请出示本次购买的单据。<br>
        地址：上海浦东新区南六公路699弄（两港装饰城）4支弄3幢185-191号<br>
        原东明钢材市场海昌铝业（36栋3-5号）				<br>
        电话：13764746926（黎女士） 13818603886（尚先生）<br></p>
      </Footer>
    </div>
    <!--<div data-backdrop="static" >-->
      <!--<Modal  v-model ="sumBackModal" title="提示信息" :mask-closable="false">-->
        <!--<Form  :model="sumBackForm"  :label-width="120">-->
          <!--<FormItem label="返回交易码"  prop="retCode">-->
            <!--<Input v-model="searchBackForm.retCode" readonly></Input>-->
          <!--</FormItem>-->
          <!--<FormItem label="返回交易信息" prop="retMsg">-->
            <!--<Input v-model="searchBackForm.retMsg" readonly></Input>-->
          <!--</FormItem>-->
        <!--</Form>-->
      <!--</Modal >-->
    <!--</div>-->
    <!--<pagination-->
      <!--v-show="total>0"-->
      <!--:total="total"-->
      <!--:page.sync="queryParams.pageNum"-->
      <!--:limit.sync="queryParams.pageSize"-->
      <!--@pagination="getList"-->
    <!--/>-->
  </div>
</template>

<script>
  import {selectlistGoodsInfo,addGoods,checkspbhUnique,delGoods,getGoods,updateGoods} from "@/api/goods/goodsInfo";
  import {goodsHandlePrint,exportGoodsList} from "@/api/goods/goodsPrint";
  export default {
    name: "print",
    data() {
      const Column = [
        { label: '编号', prop: 'bh', width: '80', key: 'bh', edit: false ,align: 'center'},
        { label: '产品名称及规格', prop: 'cpmcjgg', minwidth: '300', key: 'cpmcjgg', edit: true ,align: 'center'},
        { label: '单位', prop: 'dw', minwidth: '140', key: 'dw', edit: true ,align: 'center'},
        { label: '数量', prop: 'sl', minwidth: '140', key: 'sl', edit: true ,align: 'center'},
        { label: '单价', prop: 'dj', minwidth: '140', key: 'dj', edit: true ,align: 'center'},
        { label: '金额', prop: 'je', minwidth: '140', key: 'je', edit: false ,align: 'center'},
        { label: '备注', prop: 'bz', minwidth: '150', key: 'bz', edit: true ,align: 'center'}
        // {
        //   label: '费用', prop: '', width: '450', align: 'center', key: '400', children: [
        //     { label: '人工费', prop: 'staff', width: '150', key: '401', edit: true },
        //     { label: '资料费', prop: 'material', width: '150', key: '402', edit: true },
        //     { label: '场地费', prop: 'site', width: '150', key: '403' }
        //   ]
        // }
      ];
      const tableData = [
        { bh:1 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:2 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:3 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:4 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:5 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:6 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:7 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:8 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:9 , cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: ""},
        { bh:10, cpmcjgg: "", dw: "", sl: '', dj: '', je: '', bz: "" }
      ]
      // "①"
      // "②"
      // "③"
      // "④"
      // "⑤"
      // "⑥"
      // "⑦"
      // "⑧"
      // "⑨"
      // "⑩"
      return {
        sumBackModal:false,
        inputVal:'',
        zje:[],
        zjeNum:0,
        flag:false,
        values:'',
        loading1:false,
        loading2:false,
        downloading:false,
        tableData: tableData,
        tableColumn: Column,
        tableHeight: 300,
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
        ifspbhUnique: '0',
        // 显示搜索条件
        showSearch: true,
        // 菜单表格树数据
        menuList: [],
        // 商品信息表格数据
        goodsInfoList: [],
        //总条数
        total: 0,
        // 默认密码
        initPassword: undefined,
        // 菜单树选项
        menuOptions: [],
        // 弹出层标题
        title: "",
        // 显示状态数据字典
        visibleOptions: [],
        // 菜单状态数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          ghdw:'',
          lxdh:'',
          djbh:'',
          djrq:'',
          zjeNum:'',
          zjeChinese:'',
        },
        //增加一条数据时检查是否存在相同的商品编号
        checkqueryParams: {
          pageNum: 1,
          pageSize: 10,
          spbh: undefined,
          spmc: undefined,
          spsx: undefined,
          spjg: undefined,
          spdw: undefined,
          spjj: undefined,
          sphyd: undefined,
          spjhdh: undefined,
          bz: undefined
        },
        columns: [
          {title: '编号', minWidth: 150,key: 'bh', align: 'center'},
          {title: '产品名称及规格', minWidth: 150,key: 'cpmcjgg', align: 'center'},
          {title: '单位',minWidth: 150,key: 'dw', align: 'center'},
          {title: '数量',minWidth: 150,key: 'sl',align: 'center'},
          {title: '单价',minWidth: 150,key: 'dj', align: 'center'},
          {title: '金额',minWidth: 150,key: 'je', align: 'center'},
          {title: '备注',minWidth: 150,key: 'bz', align: 'center'},
        ],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          spbh1: [
            { required: true, message: "商品编号不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      //获取当前年月日
      var aData = new Date();
      console.log(aData) //Wed Aug 21 2019 10:00:58 GMT+0800 (中国标准时间)
      this.queryParams.djrq =aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
      console.log(this.queryParams.djrq) //2019-8-20
      //获取当前时分秒
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      this.queryParams.djbh = 'CY'+hh+mf+ss;
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      shutDown(){
        this.flag=false
      },
      numFilter(value) {
        const realVal = parseFloat(value).toFixed(2);
        return realVal;
      },
      numFilter0(value) {
        const realVal = parseFloat(value).toFixed(0);
        return realVal;
      },
      handleEdit() {
        for(let i=0;i<this.tableData.length;i++){
          if(this.tableData[i].sl=='' && this.tableData[i].dj==''){
            this.zje[i] = 0;
            this.tableData[i].je = '';
          }else if(this.tableData[i].sl=='' && this.tableData[i].dj!=''){
            this.zje[i] = 0;
            this.tableData[i].je = '0.00';
          }else if(this.tableData[i].sl!='' && this.tableData[i].dj==''){
            this.zje[i] = 0;
            this.tableData[i].je = '0.00';
          }else{
            this.tableData[i].je = this.numFilter(parseFloat(this.tableData[i].sl) * parseFloat(this.tableData[i].dj));
            this.zje[i] = parseFloat(this.tableData[i].je);
            // alert(this.numFilter0(this.tableData[i].sl * this.tableData[i].dj))
            // this.inputVal=this.numFilter0(this.tableData[i].sl * this.tableData[i].dj)

          }
        }
        // this.inputVal = this.numFilter0(this.zjeNum);
        // this.tableData[i].je = numFilter(this.tableData[i].je);
      },
      cellClick(row, column, cell, event) {
        for(let i=0;i<document.getElementsByClassName('current-cell').length;i++){
          document.getElementsByClassName('current-cell')[i].classList.remove('current-cell');
        }
        cell.classList.add("current-cell");
      },
      removeClass(){
        document.getElementsByClassName('current-cell')[0].classList.remove('current-cell');
      },
      handlePrint(){
        this.loading1 = true;
        // 先计算总金额
        this.zjeNum=0;
        for(let j=0;j<this.zje.length;j++){
          console.log('第'+(j+1)+'个：'+this.zje[j])
          this.zjeNum=Number(parseFloat(this.zje[j]).toFixed(2))+Number(parseFloat(this.zjeNum).toFixed(2));
        }
        //金额大写和小写
        this.queryParams.zjeNum = this.numFilter0(this.zjeNum)+'.00';
        this.queryParams.zjeChinese = this.smallToBig(this.numFilter0(this.zjeNum) );

        goodsHandlePrint(this.tableData,this.queryParams).then(response => {
          this.$router.push({
              name: 'responsepdf',
              params: {
                pdfPath: response.msg
              }
            })
          this.loading1=false;
        })
      this.loading1=false;
      },
      resetPrint(name){
        this.$Modal.confirm({
          loading:true,
          title:'提示',
          content:'是否重置"' + this.queryParams.ghdw + '"的所有数据',
          okText:'重置',cancelText:'取消',
          onOk:()=>{
            this.zje = [];
            this.zjeNum = 0;
            this.queryParams.ghdw = '';
            this.queryParams.lxdh = '';
            //获取当前时分秒
            let hh = new Date().getHours();
            let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
            let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
            this.queryParams.djbh = 'CY'+hh+mf+ss;
            var aData = new Date();
            console.log(aData) //Wed Aug 21 2019 10:00:58 GMT+0800 (中国标准时间)
            this.queryParams.djrq =aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
            console.log(this.queryParams.djrq) //2019-8-20
            // this.queryParams.djbh = '';
            // this.queryParams.djrq = '';
            this.tableData = [
              { bh:1 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:2 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:3 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:4 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:5 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:6 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:7 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:8 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:9 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
              { bh:10 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: "" }
            ];
            this.$Modal.remove();
          }
        })
      },
      smallToBig(money) {
        //  将数字金额转换为大写金额
        var cnNums = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖"); //汉字的数字
        var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位
        var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位
        var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位
        var cnInteger = "整"; //整数金额时后面跟的字符
        var cnIntLast = "元"; //整数完以后的单位
        //最大处理的数字
        var maxNum = 999999999999999.9999;
        var integerNum; //金额整数部分
        var decimalNum; //金额小数部分
        //输出的中文金额字符串
        var chineseStr = "";
        var parts; //分离金额后用的数组，预定义
        if (money == "") {
          return "零元整";
        }

        money = parseFloat(money);
        if (money >= maxNum) {
          //超出最大处理数字
          return "超出最大处理数字";
        }
        if (money == 0) {
          chineseStr = cnNums[0] + cnIntLast + cnInteger;
          return chineseStr;
        }

        //四舍五入保留两位小数,转换为字符串
        money = Math.round(money * 100).toString();
        integerNum = money.substr(0, money.length - 2);
        decimalNum = money.substr(money.length - 2);

        //获取整型部分转换
        if (parseInt(integerNum, 10) > 0) {
          var zeroCount = 0;
          var IntLen = integerNum.length;
          for (var i = 0; i < IntLen; i++) {
            var n = integerNum.substr(i, 1);
            var p = IntLen - i - 1;
            var q = p / 4;
            var m = p % 4;
            if (n == "0") {
              zeroCount++;
            } else {
              if (zeroCount > 0) {
                chineseStr += cnNums[0];
              }
              //归零
              zeroCount = 0;
              chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
            }
            if (m == 0 && zeroCount < 4) {
              chineseStr += cnIntUnits[q];
            }
          }
          chineseStr += cnIntLast;
        }
        //小数部分
        if (decimalNum != "") {
          var decLen = decimalNum.length;
          for (var i = 0; i < decLen; i++) {
            var n = decimalNum.substr(i, 1);
            if (n != "0") {
              chineseStr += cnNums[Number(n)] + cnDecUnits[i];
            }
          }
        }
        if (chineseStr == "") {
          chineseStr += cnNums[0] + cnIntLast + cnInteger;
        } else if (decimalNum == "" || /^0*$/.test(decimalNum)) {
          chineseStr += cnInteger;
        }
        return chineseStr;
      },
      sumPrint(){
        this.zjeNum=0;
        this.loading2=true;
        for(let j=0;j<this.zje.length;j++){
          console.log('第'+(j+1)+'个：'+this.zje[j])
          this.zjeNum=Number(parseFloat(this.zje[j]).toFixed(2))+Number(parseFloat(this.zjeNum).toFixed(2));
        }
        const title = '计算总金额';
        const content = '<p>&#8195;实际金额：'+'￥'+this.zjeNum+'</p><p>&#8195;人民币大写:'+this.smallToBig(this.zjeNum)+'</p><p>&#8195;</p><p>&#8195;取整金额：'+'￥'+this.numFilter0(this.zjeNum)+'</p>&#8195;人民币大写:'+this.smallToBig(this.numFilter0(this.zjeNum) );
        this.$Modal.success({
          title: title,
          content: content
        });
        this.loading2=false;
      },
      exportPrint(){
        this.$Modal.confirm({
          loading:true,
          title:'提示',
          content:'是否导出"' + this.queryParams.ghdw + '"的所有数据',
          okText:'导出',cancelText:'取消',
          onOk:()=>{
            exportGoodsList(this.tableData).then(response => {
              this.download(response.msg);
            });
            this.$Modal.remove();
          }
        })
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.spbh)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 查询菜单列表 */
      getList() {
        this.loading = true;
        selectlistGoodsInfo(this.queryParams).then(response => {
          this.goodsInfoList = response.rows;
          this.total=response.total;
          this.loading = false;
        });
      },
      /**检查是否已经存在商品编号*/
      checkspbhUnique(name){
        if(this.form.spbh!='' && this.form.spbh!=null){
          this.checkqueryParams.spbh = name;
          selectlistGoodsInfo(this.checkqueryParams).then(response=>{
            this.ifspbhUnique=response.data;
            if(response.total==0){
              this.$message.error({
                content:'表中已经存在该编号，不可重复取值，请另取他值',
                duration:5,
              });
              this.form.spbh='';
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
          spbh: undefined,
          spmc: undefined,
          spsx: undefined,
          spjg: undefined,
          spdw: undefined,
          spjj: undefined,
          sphyd: undefined,
          spjhdh: undefined,
          bz: undefined
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
        this.form.spbh1=row.spbh;
        this.readonly=true;
        this.open = true;
        this.title = "修改商品信息";
        // getGoods(row.spbh).then(response => {
        //   this.form = response.data;
        //
        // });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.spbh = this.form.spbh1;
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



            // if(this.form.spbh != undefined){
            //   updateGoods(this.form).then(response => {
            //     this.msgSuccess("修改成功");
            //     this.open=false;
            //     this.getList();
            //   });
            // }else{
            //   addGoods(this.form).then(response => {
            //     this.msgSuccess("新增成功");
            //     this.open = false;
            //     this.getList();
            //   });
            // }
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
          return delGoods(row.spbh);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      }
    }
  };
</script>

<style scoped>
  .layout1{
    font-size: 30px;
    text-align: center;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
  .layout2{
    font-size: 20px;
    text-align: center;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
  .tableDiv .el-input {
    display: none;
  }
  .current-cell .el-input {
    display: block;
  }
  .current-cell .el-input + span {
    display: none;
  }
</style>
