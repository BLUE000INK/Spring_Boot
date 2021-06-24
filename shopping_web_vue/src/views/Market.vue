<template>
  <div>
    <div class="imgInfo">
      <el-carousel :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="item in img_list" :key="item">
          <el-image :src="item" fit="contain"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div>
      <div>
        <el-drawer
          title="我是标题"
          :visible.sync="drawer"
          :with-header="false">
          <el-row>
            <el-col :span="8" v-for="(goods, i) in good_list" :key="goods.goodsId" :offset="2">
              <el-card :body-style="{ padding: '0px'}">
                <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                     class="image">
                <div style="padding: 5px;">
                  <span>物品:{{ goods.goodsName }}</span>
                  <el-collapse>
                    <el-collapse-item title = "详情">
                      <div>商品类别:{{ goods.goodsType }}</div>
                      <div>商品价格:{{ goods.goodsPrice }}</div>
                      <div>是否议价:{{ goods.goodsBargin }}</div>
                      <div>商品尺寸:{{ goods.goodsSize }}</div>
                      <div>新旧程度:{{ goods.goodsNew }}</div>
                      <div>商品数量:{{ goods.goodsNumber }}</div>
                    </el-collapse-item>
                  </el-collapse>
                  <div class="bottom clearfix">
                    <el-button type="text" class="button" @click="buygoods(goods)">购买</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-drawer>
      </div>
    </div>
    <div class="storeInfo">
      <el-row>
        <el-col :span="8" v-for="(stores, i) in store_list" :key="stores.storeId" :offset="2">
          <el-card :body-style="{ padding: '0px'}">
            <img src="/static/photo/cardpng/shop.png"
                 class="image">
              <span>店铺:{{ stores.storeName }}<br><br></span>
              <el-collapse>
                <el-collapse-item title = "详情">
                  <div>等级:{{ stores.storeLevel }}</div>
                  <div>联系电话:{{ stores.storePhone }}</div>
                </el-collapse-item>
              </el-collapse>
              <div class="bottom clearfix" style="padding: 5px;">
                <el-button type="text" class="button" @click="enterstore(stores.storeName)">进入店铺</el-button>
              </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        img_list: [
          '/static/photo/homerollpng/cola.png',
          '/static/photo/homerollpng/book.png',
          '/static/photo/homerollpng/phone.png',
          '/static/photo/homerollpng/shoes.png',
        ],
        store_list: [],
        good_list:[],
        drawer: false,
      }
    },
    created () {
      this.$axios.get('http://localhost:8080/stores/getAllStoreBan').then(res => {
        this.store_list = res.data
        console.info('store_list' + this.store_list)
      })
    },
    methods: {
      enterstore(storename){
        this.drawer = true
        this.$axios.post('http://localhost:8080/goods/getAllgoods',{
          goodsStorename : storename,
        }).then(res=>{
          this.good_list = res.data
        })
      },
      buygoods(goods){
        this.$prompt('请输入数量',  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^[0-9]*$/,
          inputErrorMessage: '数量输入不正确'
        }).then(({ value }) => {
          if(value > goods.goodsNumber){
            alert("库存不足")
          }else if(sessionStorage.getItem('userMoney') < parseInt(value) * parseInt(goods.goodsPrice)){
            alert("钱包不足")
          }else{
            console.log("goodsId"+goods.goodsId)
            this.$axios.post('http://localhost:8080/goods/buygoods',{
              goodsId : parseInt(goods.goodsId),
              goodsNumber : parseInt(goods.goodsNumber) - parseInt(value),
            })
            console.log("userId = "+sessionStorage.getItem('userId'))
            this.$axios.post('http://localhost:8080/users/myupdate',{
              userId : sessionStorage.getItem('userId'),
              userScore : parseInt(sessionStorage.getItem('userScore')) + parseInt(value) * parseInt(goods.goodsPrice),
              userMoney : parseInt(sessionStorage.getItem('userMoney')) - parseInt(value) * parseInt(goods.goodsPrice),
            })
            this.$axios.post('http://localhost:8080/users/findUserinfo',{
              userName: sessionStorage.getItem('userName')
            }).then(res=>{
              sessionStorage.setItem('userMoney',res.data.userMoney);
              sessionStorage.setItem('userScore',res.data.userScore);
            })
            this.$axios.post('http://localhost:8080/info/addinfo',{
              infoUserName : sessionStorage.getItem('userName'),
              infoStoreName : goods.goodsStorename,
              infoGoodId : goods.goodsId,
              infoGoodNumber : parseInt(value),
            })

            this.$message({
              type: 'success',
              message: '购买成功,请刷新页面'
            });

          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消购买'
          });
        });
      }
    }
  }
</script>
<style>
  .el-carousel__item el-image {
    color: #2b2b2b;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item {
    width: 450px;
    height: 260px;
    left: 10%;
    right: 90%;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .storeInfo {
    border: 10px solid #3a91cf
  }

  .imgInfo {
    border: 10px solid #545c64
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
