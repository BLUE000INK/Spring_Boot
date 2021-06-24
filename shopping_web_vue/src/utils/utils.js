import axios from 'axios'
const myutils = {
  axiosMethod: (config) => {
    axios({
      method: config.method,
      url: config.url,
      params: config.parms ? config.params : null,
      data: config.data ? config.data : null

    }).then(config.callback).catch(config.catch ? config.catch : () => {})
  }
}
export default myutils
