<template>
  <div>
    <div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        style="position: relative"
        active-text-color="#ffd04b">
        <el-menu-item index="0" style="display: none"></el-menu-item>
        <router-link :to="{name:'market'} " >
          <el-menu-item index="1" @click="menu_home" style="display:inline-block;">首页</el-menu-item>
          <el-dialog
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <span style="font-family: 隶书;font-size: 30px">&nbsp&nbsp欢迎登录二手市场交易平台</span>
            <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="handleClose">好的</el-button>
  </span>
          </el-dialog>
        </router-link>
        <router-link v-if="this.visible" style="display:inline-block;" :to="{name:'adminpage'} " >
          <el-menu-item  index="9" >后台管理</el-menu-item>
        </router-link>

        <el-menu-item index="2" style="position: absolute;right: 55px;top: 0px;height: 56px;width: 100px;background-color: #579242" @click="addmoney">
          {{
            userMoney + '$'
          }}
        </el-menu-item>
        <el-menu-item index="4" style="position: absolute;right: 155px;top: 0px;height: 56px;width: 100px;background-color: #deb160">
          {{
            userScore + '积分'
          }}
        </el-menu-item>
        <el-menu-item index="5" style="position: absolute;right: 255px;top: 0px;height: 56px;width: 100px;background-color: #bb88f3">
          {{
            userCredit + '信用'
          }}
        </el-menu-item>
        <el-dropdown trigger="click" style="position: absolute;right: 0px;" @command="handleCommand">
          <el-button index="3" type="primary" icon="el-icon-user-solid" style="height: 56px">
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :command="{flag: 1}">
              <router-link :to="{name : 'userinfo'}">资料修改</router-link>
            </el-dropdown-item>
            <el-dropdown-item :command="{flag: 2}">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-menu>
      <router-view/>
    </div>
  </div>
</template>
<script>
  export default {
    inject: ['reload'],
    data () {
      return {
        activeIndex: '',
        userMoney: '',
        userScore: '',
        userCredit: '',
        dialogVisible: false,
        visible: true
      }
    },
    created () {
      console.log('sessionStorage.userMoney' + sessionStorage.getItem('userMoney'))
      if (sessionStorage.getItem('userAdmin') == 0){
        this.visible = false;
      }else{
        this.visible = true;
      }
      // this.userMoney = sessionStorage.getItem('userMoney') + ''
      // this.userCredit = sessionStorage.getItem('userCredit') + ''
      // this.userScore = sessionStorage.getItem('userScore') + ''

    },
    mounted () {
      this.userMoney = sessionStorage.getItem('userMoney') + ''
      this.userCredit = sessionStorage.getItem('userCredit') + ''
      this.userScore = sessionStorage.getItem('userScore') + ''
      if (sessionStorage.getItem('charge') == '1') {
        this.dialogVisible = true
        sessionStorage.setItem('charge', '0')
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (done) {
        this.reload()
        this.menu_home()
      },
      menu_home () {
        console.log('menu_home:跳转成功')
        this.userMoney = sessionStorage.getItem('userMoney')
        this.userScore = sessionStorage.getItem('userScore')
        this.$router.replace({name: 'homepage'})
      },
      handleCommand (command) {
        switch (command.flag) {
          case 1:
            console.log('用户资料click')
            this.activeIndex = '0'
            this.reload()
            break
          case 2:
            console.log('退出登录click')
            this.$router.replace({path: '/userlogin'})
            this.reload()
            sessionStorage.clear()
            break
          default:
            return
        }
      },
      addmoney(){
        this.$prompt('请输入充值金额',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          inputPattern: /^[0-9]*$/,
        }).then(({value})=>{
          this.$axios.post('http://localhost:8080/users/myupdate',{
            userId : sessionStorage.getItem('userId'),
            userScore : parseInt(sessionStorage.getItem('userScore')),
            userMoney : parseInt(sessionStorage.getItem('userMoney')) + parseInt(value) ,
          })
          sessionStorage.setItem('userMoney',parseInt(sessionStorage.getItem('userMoney')) + parseInt(value));
          this.$message({
            type: 'success',
            message: '充值成功,请刷新页面'
          })
          window.location.reload()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消充值'
          });
        });

      },
    }

  }
</script>


