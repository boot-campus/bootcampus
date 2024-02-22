import type { Meta, StoryObj } from '@storybook/react';
import BlockOption from './BlockOption';

/**
 * `<BlockOption>`은 `<BlockSelect>`의 하위 컴포넌트로, 옵션 하나를 나타내는 블록 모양의 컴포넌트입니다.
 */
const meta = {
  title: 'common/BlockSelect/BlockOption',
  component: BlockOption,
  argTypes: {},
} satisfies Meta<typeof BlockOption>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Checked: Story = {
  args: {
    id: 1,
    name: '롭이어 토끼',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/768ea0c5-7b53-4aa5-a3fb-6a7d7555488e',
    onClick: (id: number) => alert(`onClick(${id});`),
    isChecked: true,
  },
};

export const Unchecked: Story = {
  args: {
    id: 2,
    name: '롭이어 토끼',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/768ea0c5-7b53-4aa5-a3fb-6a7d7555488e',
    onClick: (id: number) => alert(`onClick(${id});`),
    isChecked: false,
  },
};

/**
 * 아이콘의 가로세로 비율이 일치하지 않는 경우에는, 비율을 유지하면서 이미지가 축소됩니다.
 */
export const NotSquareIcon: Story = {
  args: {
    id: 3,
    name: '다람쥐',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/9538c6e1-31aa-453a-b67b-39b886dd842e',
    onClick: (id: number) => alert(`onClick(${id});`),
    isChecked: false,
  },
};
