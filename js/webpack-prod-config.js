var webpack = require('webpack');
var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var scalajs = require('./scalajs.webpack.config')
var UglifyJsPlugin = require('uglifyjs-webpack-plugin');

var htmlWebpackParams = {
  "template": path.join(__dirname, "index.template.ejs"),
  "apiUrl": "API_URL",
}

module.exports = scalajs;

module.exports.output = {
  path: path.join(__dirname, 'dist'),
  "filename": "[name].[chunkhash].js",
  publicPath: '/'
};

module.exports.module = {
  rules: [
    {
      test: /\.css$/,
      loader: ExtractTextPlugin.extract({
        fallback: "style-loader",
        use: "css-loader"
      })
    },
    {
      test: /\.(ttf|otf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
      loader: 'file-loader?name=fonts/[name].[hash].[ext]'
    },
    {
      test: /\.(jpe?g|gif|png)$/,
      loader: 'file-loader?name=images/[name].[hash].[ext]',
      include: [path.join(__dirname, "images")]
    }
  ]
};

module.exports.plugins = [
  new webpack.DefinePlugin({
    'process.env': {
      'NODE_ENV': JSON.stringify('production')
    }
  }),
  new HtmlWebpackPlugin(htmlWebpackParams),
  new ExtractTextPlugin({ // define where to save the file
    filename: '[name].[chunkhash].bundle.css',
    allChunks: true
  })
]

module.exports.optimization = {
  minimizer: [
    new UglifyJsPlugin({
      cache: true,
      parallel: true,
      sourceMap: true
    })
  ]
};



