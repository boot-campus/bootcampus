import { styled } from 'styled-components';

export const Container = styled.li`
  display: inline-block;

  width: auto;
  height: 52px;
`;

export const Button = styled.button`
  display: inline-flex;
  align-items: center;
  column-gap: 6px;

  width: auto;
  height: 100%;
  padding: 8px;

  border: 1px solid ${({ theme }) => theme.color.TEAL};
  background-color: ${({ theme }) => theme.color.WHITE};
  border-radius: 8px;
`;

export const Icon = styled.img`
  width: 30px;
  height: 30px;

  object-fit: contain;
`;

export const Name = styled.p`
  overflow: hidden;

  font-size: 20px;
  font-weight: 36px;

  text-overflow: ellipsis;
  white-space: nowrap;
`;

export const RadioButtonWrapper = styled.div`
  width: 26px;
  height: 26px;

  & > svg {
    width: 100%;
    height: 100%;

    color: ${({ theme }) => theme.color.TEAL};
  }
`;
