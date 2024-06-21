import React, {ReactNode} from 'react';
import {RowComponent} from '.';

interface Props {
  icon?: ReactNode;
  title: string;
  isFill?: boolean;
  color?: string;
}

const TagComponent = (props: Props) => {
  const {icon} = props;

  return <RowComponent>{icon && icon}</RowComponent>;
};

export default TagComponent;
