import React, { PureComponent } from "react";

class Button extends PureComponent {
  render() {
    const { name } = this.props;
    return (
      <React.Fragment>
        <div className="main__join-button">
          <h4>{name}</h4>
        </div>
      </React.Fragment>
    );
  }
}

export default Button;
