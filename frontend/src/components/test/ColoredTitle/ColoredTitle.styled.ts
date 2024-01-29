import { styled } from 'styled-components';

export const ColoredTitle = styled.h1<{ $color: 'black' | 'teal' }>`
  color: ${({ theme, $color }) =>
    $color === 'teal' ? theme.color.TEAL : 'black'};
`;
