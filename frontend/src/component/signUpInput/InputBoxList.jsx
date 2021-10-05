import React, { PureComponent } from 'react';


class InputBoxList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <label>{this.props.title}</label>
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

export default InputBoxList;