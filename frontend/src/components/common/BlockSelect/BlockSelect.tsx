import * as S from './BlockSelect.styled';
import BlockOption from './BlockOption';
import type { OptionItem } from '~types/common';

type BlockSelectProps = {
  options: OptionItem[];
  selectedOptionId: number;
  onChange: (id: number) => void;
};

const BlockSelect = (props: BlockSelectProps) => {
  const { options, selectedOptionId, onChange } = props;
  return (
    <S.Container>
      {options.map((option) => (
        <BlockOption
          key={option.id}
          isChecked={option.id === selectedOptionId}
          onClick={onChange}
          {...option}
        />
      ))}
    </S.Container>
  );
};

export default BlockSelect;
