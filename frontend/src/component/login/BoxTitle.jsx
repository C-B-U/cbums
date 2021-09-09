import React, { PureComponent } from "react";
import BoxDeco from "../../images/login/boxDeco.png";
class BoxTitle extends PureComponent {
  render() {
    const { title } = this.props;
    return (
      <React.Fragment>
        <img src={BoxDeco} alt="boxDeco" className="article__boxDeco" />
        <h1 className="article__boxTitle">{title}</h1>
      </React.Fragment>
    );
  }
}

export default BoxTitle;
