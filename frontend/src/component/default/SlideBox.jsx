import React, { PureComponent } from "react";
import style from "../../css/default/default.module.css";

class SlideBox extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      studyName: "",
      Color: "",
    };
  }
  render() {
    const { studyName } = this.props;
    const { Color } = this.props;

    return (
      <React.Fragment>
        <div
          className={style['main__slide-box']}
          style={{ backgroundColor: Color }}
        ></div>
        <h3>{studyName}　</h3>
      </React.Fragment>
    );
  }
}

export default SlideBox;
