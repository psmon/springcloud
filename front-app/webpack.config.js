  var path = require('path');
  var HtmlWebpackPlugin = require('html-webpack-plugin');

  module.exports = {
    devServer: {
      contentBase: path.resolve(__dirname, '.'),
      compress: true,
      port: 9000
    },
      entry: './src/app.js',
      devtool: 'sourcemaps',
      cache: true,
      mode: 'development',
      output: {
          path: __dirname,
          publicPath:'/src/',
          filename: '../front-web/src/main/resources/static/built/bundle.js'

      },
      module: {
          rules: [
              {
                  test: path.join(__dirname, '.'),
                  exclude: /(node_modules)/,
                  use: [{
                      loader: 'babel-loader',
                      options: {
                          presets: ["@babel/preset-env", "@babel/preset-react"]
                      }
                  }]
              }
          ]
      }/*,
      plugins: [
          new HtmlWebpackPlugin({
            template: path.join(__dirname, './src/index.html'),
            inject: false,
            filename: path.join(__dirname, '../app/dist/index.html')
          })
      ]*/
  };