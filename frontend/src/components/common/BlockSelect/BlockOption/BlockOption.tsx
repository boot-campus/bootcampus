import * as S from './BlockOption.styled';
import { RadioButtonChecked, RadioButtonUnchecked } from '~images/svg';
import type { OptionItem } from '~types/common';

type BlockOptionProps = OptionItem & {
  isChecked: boolean;
  onClick: (id: number) => void;
};

const BlockOption = (props: BlockOptionProps) => {
  const { id, name, image, isChecked, onClick } = props;

  return (
    <S.Container>
      <S.Button
        onClick={() => onClick(id)}
        aria-label={`${name} ${isChecked ? '선택됨' : '선택하기'}`}
      >
        {image && <S.Icon src={image} alt={name} />}
        <S.Name>{name}</S.Name>
        <S.RadioButtonWrapper>
          {isChecked ? <RadioButtonChecked /> : <RadioButtonUnchecked />}
        </S.RadioButtonWrapper>
      </S.Button>
    </S.Container>
  );
};

export default BlockOption;
