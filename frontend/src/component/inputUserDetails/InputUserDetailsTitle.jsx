import React, { PureComponent } from "react";
import BoxDeco from "../../images/login/boxDeco.png";

class InputUserDetailsTitle extends PureComponent { 
    render() {
    const { title } = this.props;
    const { imageClass } = this.props;
    const { titleClass } = this.props;
    return (
      <React.Fragment>
        <img src={BoxDeco} alt="boxDeco" className={imageClass} />
        <h1 className={titleClass}>{title}</h1>
      </React.Fragment>
    );
  }
}

export default InputUserDetailsTitle;