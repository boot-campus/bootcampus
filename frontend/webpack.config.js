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
  resolve: {
    extensions: ['.tsx', '.ts', '.js'],
    alias: {
      '~components': resolve(__dirname, 'src/components'),
      '~constants': resolve(__dirname, 'src/constants'),
      '~domains': resolve(__dirname, 'src/domains'),
      '~hooks': resolve(__dirname, 'src/hooks'),
      '~styles': resolve(__dirname, 'src/styles'),
      '~images': resolve(__dirname, 'src/images'),
      '~types': resolve(__dirname, 'src/types'),
      '~utils': resolve(__dirname, 'src/utils'),
    },
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: join(__dirname, './public/index.html'),
    }),
  ],
};
