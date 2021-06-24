<template>
  <div>
    <div style="border: 10px solid">
      <span style="font-family: 隶书;font-size: 50px">商户信息</span>
      <el-table
        :data="tablestoreData.filter(data => !search1 || data.storeName.toLowerCase().includes(search1.toLowerCase()))"
        style="width: 100%">
        <el-table-column
          label="id"
          prop="storeId">
        </el-table-column>
        <el-table-column
          label="店户名"
          prop="storeName">
        </el-table-column>
        <el-table-column
          label="店户等级"
          prop="storeLevel">
        </el-table-column>
        <el-table-column
          label="封禁状态"
          prop="storeBan">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope1">
            <el-input
              v-model="search1"
              size="mini"
              placeholder="输入店户名关键字搜索"/>
          </template>
          <template slot-scope="scope1">
            <el-button
              size="mini"
              @click="handleEdit1(scope1.$index, scope1.row)">Edit
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete1(scope1.$index, scope1.row)">Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="border: 10px solid #5c9748;">
      <span style="font-family: 隶书;font-size: 50px">用户信息</span>
      <el-table
        :data="tableuserData.filter(data => !search2 || data.userName.toLowerCase().includes(search2.toLowerCase()))"
        style="width: 100%">
        <el-table-column
          label="id"
          prop="userId">
        </el-table-column>
        <el-table-column
          label="用户名"
          prop="userName">
        </el-table-column>
        <el-table-column
          label="积分"
          prop="userScore">
        </el-table-column>
        <el-table-column
          label="信用"
          prop="userCredit">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope2">
            <el-input
              v-model="search2"
              size="mini"
              placeholder="输入用户名关键字搜索"/>
          </template>
          <template slot-scope="scope2">
            <el-button
              size="mini"
              @click="handleEdit21(scope2.$index, scope2.row)" icon="el-icon-circle-plus">信用
            </el-button>
            <el-button
              size="mini"
              @click="handleEdit22(scope2.$index, scope2.row)" icon="el-icon-remove">信用
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete2(scope2.$index, scope2.row)">Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="border: 10px solid #5a5ed3;">
      <span style="font-family: 隶书;font-size: 50px">购买记录</span>
      <el-table
        :data="tableinfoData.filter(data => !search3 || data.infoUserName.toLowerCase().includes(search3.toLowerCase()))"
        style="width: 100%">
        <el-table-column
          label="用户名"
          prop="infoUserName">
        </el-table-column>
        <el-table-column
          label="店名"
          prop="infoStoreName">
        </el-table-column>
        <el-table-column
          label="商品编号"
          prop="infoGoodId">
        </el-table-column>
        <el-table-column
          label="购买数量"
          prop="infoGoodNumber">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot="header" slot-scope="scope3">
            <el-input
              v-model="search3"
              size="mini"
              placeholder="输入用户名关键字搜索"/>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        tablestoreData: [{}],
        tableuserData: [{}],
        tableinfoData: [{}],
        search1: '',
        search2: '',
        search3: '',
      }
    },
    created () {
      this.$axios.get('http://localhost:8080/info/getAllinfo').then(res => {
        this.tableinfoData = res.data
      })
      this.$axios.get('http://localhost:8080/stores/getAllStore').then(res => {
        this.tablestoreData = res.data
      })
      this.$axios.get('http://localhost:8080/users/getAllUser').then(res => {
        this.tableuserData = res.data
      })
    },
    methods: {
      handleEdit1 (index, row) {
        let a = row.storeBan
        console.log(index, row)
        if(row.storeBan == 0){
          this.$axios.post('http://localhost:8080/stores/BanStore',{
            storeId : row.storeId,
            storeBan : '1',
          })
          alert("店户封禁成功")
          window.location.reload()
        }else{
          this.$axios.post('http://localhost:8080/stores/BanStore',{
            storeId : row.storeId,
            storeBan : '0',
          })
          alert("店户解封成功")
          window.location.reload()
        }

      },
      handleEdit21 (index, row) {
        console.log(index, row)
        if(row.userCredit >= 5){
          alert("信用满级，不可提升")
        }else{
          this.$axios.post('http://localhost:8080/users/UserCredit21',{
            userId : row.userId,
            userCredit : parseInt(row.userCredit + 1),
          })
          sessionStorage.setItem('userCredit', parseInt(row.userCredit + 1))
          alert("信用提升成功")
          window.location.reload()
        }
      },
      handleEdit22 (index, row) {
        console.log(index, row)
        if(row.userCredit <= 0){
          alert("用户信用已达最低信用")
        }else{
          this.$axios.post('http://localhost:8080/users/UserCredit22',{
            userId : row.userId,
            userCredit : parseInt(row.userCredit - 1),
          })
          sessionStorage.setItem('userCredit', parseInt(row.userCredit - 1))
          alert("信用降级成功")
          window.location.reload()
        }
      },
      handleDelete1 (index, row) {
        console.log(index, row)
        this.$axios.post('http://localhost:8080/stores/DeletStore',{
          storeId : row.storeId,
        })
        alert("删除成功")
        window.location.reload()
      },
      handleDelete2 (index, row) {
        console.log(index, row)
        this.$axios.post('http://localhost:8080/users/DeletUser',{
          userId : row.userId,
        })
        alert("删除成功")
        window.location.reload()
      }
    },
  }
</script>
<style>

</style>
