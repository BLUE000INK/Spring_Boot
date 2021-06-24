<template>
  <div class="login-container" align="center">
    <div style="width: 50%">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
        <label style="font-size: 50px;font-family: 隶书">店户登录</label>
        <el-form-item prop="name">
          <el-input v-model="ruleForm.name" placeholder="店户名" clearable></el-input>
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
            {required: true, message: '请输入店户名', trigger: 'blur'}
          ],
          pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      //登录按钮
      btn_LoginForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('http://localhost:8080/stores/findStore', {
              storeName: this.ruleForm.name ,
              storePwd: this.ruleForm.pwd ,
            }).then(response => {
              console.log('response'+response.data)
              if (response.data == 1) {
                sessionStorage.setItem('storeName',this.ruleForm.name);
                this.$axios.post('http://localhost:8080/stores/findStoreinfo', {
                  storeName: sessionStorage.getItem('storeName')
                }).then(response => {
                  console.log(response);
                  sessionStorage.setItem('storeLevel',response.data.storeLevel);
                  sessionStorage.setItem('storeBan',response.data.storeBan);
                  // alert('storeban='+sessionStorage.getItem('storeBan'))
                  // alert('storelevel='+sessionStorage.getItem('storeLevel'))
                })
                if(sessionStorage.getItem('storeBan') == '0'){
                  this.$router.push({name: 'storemanage'})
                }else if(sessionStorage.getItem('storeBan') == '1'){
                  alert('正在审核中。。。')
                }
              }else if(response.data == 2){
                alert('该店户不存在');
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
        this.$router.push({name: 'storeregist'})
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
