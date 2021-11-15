import React, { PureComponent } from "react";

class LoginButton extends PureComponent {
  render() {
    const { title } = this.props;
    return (
      <React.Fragment>
        <a href={this.props.href}>
        <div className={this.props.className} style={this.props.bgStyle} onClick={this.props.onClick}>
          <img
            width={this.props.width}
            src={this.props.src}
            alt={this.props.alt}
          />
          <span style={this.props.tStyle}>{title}</span>
        </div>
        </a>
      </React.Fragment>
    );
  }
}

export default LoginButton;