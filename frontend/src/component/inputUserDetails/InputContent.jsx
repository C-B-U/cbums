import React, { PureComponent } from 'react';
import styled from 'styled-components';

const Label = styled.label`
  font-weight: bold;
  display: block;
`;

class InputContent extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Label>{this.props.title}</Label>
        <input
          className={this.props.className}
          type={this.props.type}
          required
          placeholder={this.props.placeholder}
          name={this.props.name}
        />
      </React.Fragment>
    );
  }
}

export default InputContent;