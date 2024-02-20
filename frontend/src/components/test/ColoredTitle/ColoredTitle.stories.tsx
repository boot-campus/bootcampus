import type { Meta, StoryObj } from '@storybook/react';
import ColoredTitle from './ColoredTitle';

/**
 * `ColortedTitle`은 색상을 설정할 수 있는 컴포넌트이자, **작동 확인을 위한 테스트용 컴포넌트입니다.**
 *
 * 이 컴포넌트는 **실제로는 프로젝트에 사용되지 않습니다.** 직접 컴포넌트의 파라미터를 조작해 보면서 이 컴포넌트가 어떻게 작동하는지 테스트 해 보세요!
 *
 */
const meta = {
  title: 'test/ColoredTitle',
  component: ColoredTitle,
  argTypes: {
    children: {
      description: '표시하고자 하는 텍스트를 의미합니다.',
    },
    color: {
      description:
        '글씨의 색상을 의미합니다. 이 컴포넌트는 색상으로 `teal`, `black` 중 하나만을 선택할 수 있습니다.',
    },
  },
} satisfies Meta<typeof ColoredTitle>;

export default meta;

type Story = StoryObj<typeof meta>;

/**
 * `color`를 `teal`로 설정했을 때의 결과입니다.
 */
export const Teal: Story = {
  args: {
    color: 'teal',
    children: 'Hello, BootCampus!',
  },
};

/**
 * `color`를 `black`로 설정했을 때의 결과입니다.
 */
export const Black: Story = {
  args: {
    color: 'black',
    children: 'Hello, BootCampus!',
  },
};
