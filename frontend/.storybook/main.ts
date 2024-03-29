import { resolve } from 'path';
import type { StorybookConfig } from '@storybook/react-webpack5';
import type { RuleSetRule } from 'webpack';

const config: StorybookConfig = {
  stories: ['../src/**/*.mdx', '../src/**/*.stories.@(js|jsx|mjs|ts|tsx)'],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@storybook/addon-onboarding',
    '@storybook/addon-interactions',
  ],
  framework: {
    name: '@storybook/react-webpack5',
    options: {
      builder: {
        useSWC: true,
      },
    },
  },
  swc: () => ({
    jsc: {
      transform: {
        react: {
          runtime: 'automatic',
        },
      },
    },
  }),
  docs: {
    autodocs: true,
  },
  webpackFinal: async (config) => {
    if (config.resolve) {
      config.resolve.alias = {
        ...config.resolve.alias,
        '~components': resolve(__dirname, '../src/components'),
        '~constants': resolve(__dirname, '../src/constants'),
        '~domains': resolve(__dirname, '../src/domains'),
        '~hooks': resolve(__dirname, '../src/hooks'),
        '~styles': resolve(__dirname, '../src/styles'),
        '~images': resolve(__dirname, '../src/images'),
        '~types': resolve(__dirname, '../src/types'),
        '~utils': resolve(__dirname, '../src/utils'),
      };
    }

    const assetRules = config.module?.rules?.find((rule) => {
      const test = (rule as { test: RegExp }).test;

      return test.test('.svg');
    }) as RuleSetRule;

    assetRules.exclude = /\.svg$/;

    config.module?.rules?.push({
      test: /\.svg$/,
      use: ['@svgr/webpack', 'url-loader'],
    });

    return config;
  },
};

export default config;
