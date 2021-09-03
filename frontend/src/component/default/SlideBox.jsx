import React, { PureComponent } from "react";
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
        <div class="main__slide-box" style={{ backgroundColor: Color }}></div>
        <h3>{studyName}　</h3>
      </React.Fragment>
    );
  }
}

export default SlideBox;
