<template>
  <div>
    <div>
      <div style="position: relative;height: 50px;border:10px solid #3a91cf">
        <el-button style="height: 100%;font-size: 20px;position: absolute">店名:{{ storename }}</el-button>
        <el-button style="height: 100%;font-size: 20px;left: 106px;position: absolute">等级:{{ storeleve }}</el-button>
        <el-button style="height: 100%;right: 0px; font-size: 20px;position: absolute;" icon="el-icon-plus" @click="addgoods"></el-button>
      </div>
      <el-table
        :data="tableData.filter(data => !search || data.goodsName.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column
          label="Name"
          prop="goodsName">
        </el-table-column>
        <el-table-column
          label="Type"
          prop="goodsType">
        </el-table-column>
        <el-table-column
          label="Price"
          prop="goodsPrice">
        </el-table-column>
        <el-table-column
          label="Size"
          prop="goodsSize">
        </el-table-column>
        <el-table-column
          label="Bargin"
          prop="goodsBargin">
        </el-table-column>
        <el-table-column
          label="New"
          prop="goodsNew">
        </el-table-column>
        <el-table-column
          label="Number"
          prop="goodsNumber">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入物品名关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">Edit
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div >
      <el-dialog :visible.sync="updateform" style="position: fixed">
        <div class="login-container" align="center">
        <div style="width:50%">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" :visible.sync="updateform">
            <label style="font-size: 50px;font-family: 隶书">{{formName}}</label>
            <el-form-item prop="name">
              <el-input v-model="ruleForm.name" placeholder="商品名" clearable></el-input>
            </el-form-item>
            <el-form-item prop="type">
              <el-input v-model="ruleForm.type" placeholder="类型"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="price">
              <el-input v-model="ruleForm.price" placeholder="价格"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="size">
              <el-input v-model="ruleForm.size" placeholder="尺寸"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="bargin">
              <el-input v-model="ruleForm.bargin" placeholder="是否议价"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="number">
              <el-input v-model="ruleForm.number" placeholder="数量"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="新度">
              <el-input v-model="ruleForm.new" placeholder="新度"  clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 100%" @click="btn_choice" round>{{formsubmit}}</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="info" style="width: 100%" @click="updateform = false" round>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
        </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        storename: '',
        storeleve: '',
        tableData: [],
        search: '',
        updateform : false,
        charge: '0',
        formName : "商品更新",
        formsubmit : "修改",
        ruleForm: {
          id:0,
          name: '',
          type: '',
          price: '',
          size: '',
          bargin: 0,
          number: 1,
          new: 10,
        },
      }
    },
    created () {
      this.storename = sessionStorage.getItem('storeName')
      this.storeleve = sessionStorage.getItem('storeLevel')
      this.$axios.post('http://localhost:8080/goods/getAllgoods', {
        goodsStorename: this.storename,
      }).then(res => {
        this.tableData = res.data
      })
    },
    methods: {
      handleEdit (index, row) {
        this.charge = '0'
        this.formsubmit = '修改'
        this.formName = '商品更新'
        console.log(index, row)
        this.updateform = true
        this.ruleForm.name = row.goodsName
        this.ruleForm.type = row.goodsType
        this.ruleForm.price = row.goodsPrice
        this.ruleForm.size = row.goodsSize
        this.ruleForm.bargin = row.goodsBargin
        this.ruleForm.number = row.goodsNumber
        this.ruleForm.new = row.goodsNew
        this.ruleForm.id = row.goodsId
        this.reload()
      },
      handleDelete (index, row) {
        console.log(index, row)
        this.$axios.post('http://localhost:8080/goods/deletgoods', {
          goodsId: row.goodsId,
        })
        alert("物品删除成功,请刷新页面")
        window.location.reload()
      },
      btn_choice(){
        if(this.charge == '0'){
          this.$axios.post('http://localhost:8080/goods/updategoods',{
            goodsId : this.ruleForm.id,
            goodsName : this.ruleForm.name,
            goodsType : this.ruleForm.type,
            goodsPrice : this.ruleForm.price,
            goodsSize :this.ruleForm.size,
            goodsBargin :this.ruleForm.bargin,
            goodsNumber :this.ruleForm.number,
            goodsNew : this.ruleForm.new
          })
          this.updateform = false
          alert("物品修改成功,请刷新页面")
          window.location.reload()
        }else if(this.charge == '1'){
          this.$axios.post('http://localhost:8080/goods/insertgoods',{
            goodsName : this.ruleForm.name,
            goodsType : this.ruleForm.type,
            goodsPrice : this.ruleForm.price,
            goodsSize :this.ruleForm.size,
            goodsBargin :this.ruleForm.bargin,
            goodsNumber :this.ruleForm.number,
            goodsNew : this.ruleForm.new,
            goodsStorename : this.storename
          })
          this.updateform = false
          alert("物品添加成功,请刷新页面")
          window.location.reload()
        }
      },
      addgoods(){
        this.charge = '1'
        this.formsubmit = '提交'
        this.formName = '商品新添'
        this.updateform = true
      },

    },
  }
</script>
<style>
  .login-container {

    border: 3px solid #4a88c7;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    background-color: #42b983;
  }
</style>
