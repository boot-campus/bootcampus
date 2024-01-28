const { resolve, join } = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const isProduction = process.env.NODE_ENV === 'production';

module.exports = {
  mode: isProduction ? 'production' : 'development',
  entry: './src/index.tsx',
  output: {
    filename: 'bundle.js',
    path: resolve(__dirname, 'dist'),
    clean: true,
  },
  devServer: {
    static: {
      directory: join(__dirname, 'public'),
    },
    historyApiFallback: true,
    port: 3000,
    compress: true,
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js'],
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: 'ts-loader',
        exclude: /node_modules/,
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      },
      {
        test: /\.(png|webp|jpg|gif|svg)$/,
        type: 'asset/resource',
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: join(__dirname, './public/index.html'),
    }),
  ],
};
