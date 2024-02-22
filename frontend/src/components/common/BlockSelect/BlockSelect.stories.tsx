import type { Meta, StoryObj } from '@storybook/react';
import BlockSelect from './BlockSelect';

/**
 * `<BlockSelect>`는 여러 옵션들 중 사용자가 하나를 선택할 수 있는, 블록 모양의 컴포넌트입니다.
 */
const meta = {
  title: 'common/BlockSelect',
  component: BlockSelect,
  argTypes: {},
} satisfies Meta<typeof BlockSelect>;

export default meta;

type Story = StoryObj<typeof meta>;

const options = [
  {
    id: 1,
    name: '롭이어 토끼',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/768ea0c5-7b53-4aa5-a3fb-6a7d7555488e',
  },
  {
    id: 2,
    name: '다람쥐',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/9538c6e1-31aa-453a-b67b-39b886dd842e',
  },
  {
    id: 3,
    name: '초콜릿 도넛',
    image:
      'https://github.com/boot-campus/bootcampus/assets/87642422/7ee097bb-22f0-4288-aa56-24a377826fce',
  },
];

export const Default: Story = {
  args: {
    options,
    selectedOptionId: 1,
    onChange: (id: number) => {
      alert(`onChange(${id});`);
    },
  },
};
