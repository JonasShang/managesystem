<template>
  <div class="demo-split">
    <Split v-model="split1">
      <div slot="left" class="demo-split-pane" style="width:100%;height:800px">
        <p class="layout1">铝材购销合同</p><br>
        <el-form ref="queryParams" :model="queryParams" :rules="rules" label-width="80px"  v-show="showSearch">
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="甲方 " prop="jf">
                <el-input v-model="queryParams.jf" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="乙方 " prop="yf">
                <el-input v-model="queryParams.yf" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="法定代表 " prop="jffddbr">
                <el-input v-model="queryParams.jffddbr" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="法定代表 " prop="yffddbr">
                <el-input v-model="queryParams.yffddbr" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="委托代理 " prop="jfwtdlr">
                <el-input v-model="queryParams.jfwtdlr" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="委托代理 " prop="yfwtdlr">
                <el-input v-model="queryParams.yfwtdlr" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="电话 " prop="jfdh">
                <el-input v-model="queryParams.jfdh" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="电话 " prop="yfdh">
                <el-input v-model="queryParams.yfdh" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="地址 " prop="jfdz">
                <el-input v-model="queryParams.jfdz" clearable/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="地址 " prop="yfdz">
                <el-input v-model="queryParams.yfdz" clearable/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="30" :offset="1">
              <el-form-item label="日期" prop="jfrq">
                <el-date-picker v-model="queryParams.jfrq" clearable transfer/>
              </el-form-item>
            </el-col>
            <el-col :span="30" :offset="1">
              <el-form-item label="日期" prop="yfrq">
                <el-date-picker v-model="queryParams.yfrq" clearable transfer/>
              </el-form-item>
            </el-col>
          </el-row>
          <div style="text-align: center">
            <el-form-item>
              <!--icon图标链接-->
              <!--https://element.eleme.cn/#/zh-CN/component/icon#tu-biao-ji-he-->
              <el-button type="primary" size="mini" icon="el-icon-search" @click="handlePrint" :loading="loading1">打印</el-button>
              <el-button type="danger" icon="el-icon-refresh" size="mini" @click="resetPrint('rules')">重置</el-button>
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
          <Footer><p>铝材购销合同使用方法：<br>
            ✔   1.右方铝材购销合同样板可点击放大进行对照<br>
            ✔   2.对照合同样板，将所需字段填入，然后点击打印即可</p><p>特别提示 甲方为购买方，乙方为销售方（默认上海昌弈金属制品有限公司）。<br>
            <!--地址 上海浦东新区南六公路699弄（两港装饰城）4支弄3幢185-191号<br>-->
            <!--原东明钢材市场海昌铝业（36栋3-5号）				<br>-->
            <!--电话 13764746926（黎女士） 13818603886（尚先生）<br>-->
          </p>
          </Footer>
        </div>
      </div>

      <div slot="right" class="demo-split-pane">
        <!--铝材购销合同样板-->
        <!--<iframe id="iFrame1" name="iFrame1" frameborder="0" :src="pdfPath1" style="height:450px" scrolling="no"></iframe>-->
        <viewer>
          <img src="../../../../../domain/img/铝合金断桥铝型材门窗制作及安装合同.jpg" height=480px width=100%>
        </viewer>
        <!--<img src="../../../../../domain/img/铝合金断桥铝型材门窗制作及安装合同.jpg" height=480px width=100%/>-->
      </div>
    </Split>
  </div>
</template>

<script>
  import {goodsContractPrint} from "@/api/goods/goodsContract"
  export default {
    name: "contract",
    data () {
      const Column = [
        { label: '编号', prop: 'bh', width: '50', key: 'bh', edit: true ,align: 'center'},
        { label: '品名', prop: 'pm', minwidth: '80', key: 'pm', edit: true ,align: 'center'},
        { label: '规格', prop: 'gg', minwidth: '60', key: 'gg', edit: true ,align: 'center'},
        { label: '单位', prop: 'dw', minwidth: '60', key: 'dw', edit: true ,align: 'center'},
        { label: '数量', prop: 'sl', minwidth: '60', key: 'sl', edit: true ,align: 'center'},
        { label: '单价/元', prop: 'dj', minwidth: '60', key: 'dj', edit: true ,align: 'center'},
        { label: '金额/元', prop: 'je', minwidth: '60', key: 'je', edit: false ,align: 'center'},
      ];
      const tableData = [
        { bh:1, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
        { bh:2, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
        { bh:3, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
        { bh:4, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
        { bh:5, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
        { bh:6, pm:'' , gg: '', dw: '', sl: '', dj: '', je: ''},
      ]
      return {
        zje:[],
        zjeNum:0,
        split1: 0.73,
        loading:false,
        pdfPath1:'',
        tableData: tableData,
        tableColumn: Column,
        tableHeight: 175,
        // 显示搜索条件
        showSearch: true,
        queryParams:{
          jf: undefined,
          yf: undefined,
          jffddbr: undefined,
          yffddbr: undefined,
          jfwtdlr: undefined,
          yfwtdlr: undefined,
          jfdh: undefined,
          yfdh: undefined,
          jfdz: undefined,
          yfdz: undefined,
          jfrq: undefined,
          yfrq: undefined,
        },
        rules: {
          jf: [
            { required: true, message: "甲方（购买方）不能为空", trigger: "blur" }
          ],
          yf: [
            { required: true, message: "乙方（销售方）不能为空", trigger: "blur" }
          ]
        }
      }
    },
    mounted: function() {
      this.queryParams.yf = '上海昌弈金属制品有限公司';
      this.queryParams.yfdh = '13764746926';
      this.queryParams.yfdz = '上海浦东新区南六公路699弄（两港装饰城）4支弄3幢185-191号';
      //获取当前年月日
      var aData = new Date();
      console.log(aData) //Wed Aug 21 2019 10:00:58 GMT+0800 (中国标准时间)
      this.queryParams.jfrq =aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
      this.queryParams.yfrq =this.queryParams.jfrq;
      console.log(this.queryParams.djrq) //2019-8-20
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
          console.log('第'+(j+1)+'个 '+this.zje[j])
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
        // this.$Modal.confirm({
        //   loading:true,
        //   title:'提示',
        //   content:'是否清空"' + this.queryParams.ghdw + '"的所有数据（编号、日期除外）',
        //   okText:'清空',cancelText:'取消',
        //   onOk:()=>{
        //     this.zje = [];
        //     this.zjeNum = 0;
        //     this.queryParams.ghdw = '';
        //     this.queryParams.lxdh = '';
        //     // this.queryParams.djbh = '';
        //     // this.queryParams.djrq = '';
        //     this.tableData = [
        //       { bh:1 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:2 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:3 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:4 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:5 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:6 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:7 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:8 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:9 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: ""},
        //       { bh:10 , cpmcjgg: "", dw: "", sl: "", dj: "", je: "", bz: "" }
        //     ];
        //     this.$Modal.remove();
        //   }
        // })
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
          console.log('第'+(j+1)+'个 '+this.zje[j])
          this.zjeNum=Number(parseFloat(this.zje[j]).toFixed(2))+Number(parseFloat(this.zjeNum).toFixed(2));
        }
        const title = '计算总金额';
        const content = '<p>&#8195;实际金额 '+'￥'+this.zjeNum+'</p><p>&#8195;人民币大写:'+this.smallToBig(this.zjeNum)+'</p><p>&#8195;</p><p>&#8195;取整金额 '+'￥'+this.numFilter0(this.zjeNum)+'</p>&#8195;人民币大写:'+this.smallToBig(this.numFilter0(this.zjeNum) );
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
  }
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
  .demo-split{
    height: 800px;
    border: 1px solid #dcdee2;
  }
  .demo-split-pane{
    padding: 10px;
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
