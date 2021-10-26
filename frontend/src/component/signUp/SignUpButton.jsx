import React, { PureComponent } from 'react';

class SingUpButton extends PureComponent {
  render() {
    const { title } = this.props;
    return (
      <React.Fragment>
        <div className={this.props.className} style={this.props.bgStyle}>
          <img
            width={this.props.width}
            src={this.props.src}
            alt={this.props.alt}
          />
          <span style={this.props.tStyle}>{title}</span>
        </div>
      </React.Fragment>
    );
  }
}

export default SingUpButton;