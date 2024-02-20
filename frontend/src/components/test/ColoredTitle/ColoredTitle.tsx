import * as S from './ColoredTitle.styled';

interface ColoredTitleProps {
  children: string;
  color: 'black' | 'teal';
}

const ColoredTitle = (props: ColoredTitleProps) => {
  const { color, children } = props;

  return <S.ColoredTitle $color={color}>{children}</S.ColoredTitle>;
};

export default ColoredTitle;
