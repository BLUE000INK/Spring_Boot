<template>
  <div class="login-container" align="center">
    <div style="width: 50%">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
        <label style="font-size: 50px;font-family: 隶书">用户登录</label>
        <el-form-item prop="name">
          <el-input v-model="ruleForm.name" placeholder="用户名" clearable></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input v-model="ruleForm.pwd" placeholder="密码" show-password clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="btn_LoginForm('ruleForm')" round>登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="info" style="width: 100%" @click="btn_Regist" round>注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>


  export default {
    data () {
      return {
        ruleForm: {
          name: '',
          pwd: '',
        },
        rules: {
          name: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
        }
      }
    },
    created () {
      sessionStorage.clear()
    },
    methods: {
      //登录按钮
      btn_LoginForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!')
            console.log(this.ruleForm.name + '   ' + this.ruleForm.pwd)
            this.$axios.post('http://localhost:8080/users/findUser', {
              userName: this.ruleForm.name,
              userPwd: this.ruleForm.pwd,
            }).then(response => {
              console.log(response)
              if (response.data == 1) {
                sessionStorage.setItem('userName',this.ruleForm.name);
                  this.$axios.post('http://localhost:8080/users/findUserinfo', {
                  userName: sessionStorage.getItem('userName')
                }).then(response => {
                  console.log(response);
                  sessionStorage.setItem('userMoney',response.data.userMoney);
                  sessionStorage.setItem('userScore',response.data.userScore);
                  sessionStorage.setItem('userCredit',response.data.userCredit);
                  sessionStorage.setItem('userId',response.data.userId);
                  sessionStorage.setItem('userAdmin',response.data.userAdmin);
                  console.log('session_usermoney+'+sessionStorage.getItem('userAdmin') );
                })
                sessionStorage.setItem('charge','1')
                console.log("get"+sessionStorage.getItem('userName'));
                this.$router.push({name: 'homepage'})
              }else if(response.data == 2){
                alert('该用户不存在');
              }else if(response.data == 0){
                alert('密码错误')
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      //注册按钮
      btn_Regist () {
        this.$router.push({name: 'userregist'})
      }
    }
  }
</script>
<style>
  .login-container {
    margin-top: 5%;
    margin-left: 33%;
    margin-right: 33%;
    border: 3px solid #4a88c7;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    background-color: #42b983;
  }
</style>
