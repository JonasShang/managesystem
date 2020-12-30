<template>
    <div >
        <iframe id="iFrame1" name="iFrame1" frameborder="0" :src=this.$route.params.pdfPath style=" width:100%;height:780px" scrolling="no"></iframe>
    </div>
</template>
<script>
    import {formatDate, updatePdf,sendPdf} from '@/api/responsepdf/pdf';

    export default {
        name: 'responsepdf',
        data() {
            return {
                // pdfPath:""
            }
        },


        // mounted() {
        //   alert(this.$route.params.pdfPath);
        //     this.pdfPath =this.$route.params.pdfPath
        // }
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

