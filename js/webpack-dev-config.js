var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = require('./scalajs.webpack.config');

module.exports.module = module.exports.module || {};

var htmlWebpackParams = {
    "template": path.join(__dirname, "index-dev.ejs"),
    "apiUrl": "localhost:9000",
    "inject": false
}
module.exports.plugins = [
    new HtmlWebpackPlugin(htmlWebpackParams),
];

module.exports.output.publicPath = '/'

module.exports.module.rules = [
    {
        "test": new RegExp("\\.js$"),
        "enforce": "pre",
        "loader": "source-map-loader",
        "exclude": [
            path.join(__dirname, "node_modules/react-google-login")
        ]
    },
    {
        test: /\.(ttf|otf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
        loader: 'file-loader?name=fonts/[name].[ext]',
        include: [path.join(__dirname, "fonts")],
    },
    {
        test: /\.(jpe?g|gif|svg|png)$/,
        loader: 'file-loader?name=images/[name].[hash].[ext]',
        include: [path.join(__dirname, "images")]
    }
];