var path = require('path');

module.exports = {
    publicPath: "/app/",
    configureWebpack: {
      resolve: {
        alias: {
          src: path.resolve(__dirname, 'src')
        }
      },
    }
  }