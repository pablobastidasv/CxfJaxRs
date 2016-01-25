package co.pablobastidasv.cxfjaxrs.entities;

import lombok.Data;

/**
 * DTO of attachments
 *
 * @author pbastidas
 */
@Data
public class Attachment {
    private String id;
    private String name;
    private String mimeType;
    private Long size;
    private byte[] content;
    private AttachmentSource source;
    private String referenceId; // To reference the owner of the file
}
