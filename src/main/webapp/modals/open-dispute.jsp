<div
        class="modal fade bd-example-modal-lg"
        id="openDisputeModal${mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Open Dispute</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/open-dispute">
                            <div class="form-row">
                                <div class="col">
                                    <label for="mailId">Package ID</label>
                                    <input class="form-control select-filter" value="${mail.mailId}" name="mailId"
                                              id="mailId" required readonly/>
                                </div>
                                <div class="col">
                                    <label for="disputeType">Select Dispute</label>
                                    <select name="disputeType" id="disputeType" class="custom-select" aria-label="Default select example" required>
                                        <option value="Faulty Product">Faulty Product</option>
                                        <option value="Did not arive">Did not arrive</option>
                                        <option value="Wrong Product">Wrong Product</option>
                                        <option value="Other">Other (Please Specify)</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="description">Description</label>
                                    <textarea class="form-control select-filter" name="description"
                                              id="description" required></textarea>
                                </div>
                            </div><hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Open Dispute
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>