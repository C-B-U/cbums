import React, { PureComponent } from "react";

class Button extends PureComponent {
  render() {
    const { name } = this.props;
    return (
      <React.Fragment>
        <div className={this.props.className}>
          {this.props.children}
        </div>
      </React.Fragment>
    );
  }
}

export default Button;
